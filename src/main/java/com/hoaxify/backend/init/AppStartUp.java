package com.hoaxify.backend.init;

import com.hoaxify.backend.outbox.job.EmailSendingJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
@Slf4j
public class AppStartUp implements CommandLineRunner {
  private final Scheduler scheduler;

  public AppStartUp(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Application startup...");
    final JobKey jobKey = JobKey.jobKey("emailSendingJob", "hoaxify");
    if (scheduler.checkExists(jobKey)) {
      scheduler.deleteJob(jobKey);
    }

//    final JobDetail jobDetail = JobBuilder.newJob(EmailSendingJob.class)
//      .withIdentity(jobKey.getName(), jobKey.getGroup())
//      .withDescription("Sends announcements to recipients' email addresses")
//      .storeDurably()
//      .build();
//
//    final TriggerKey triggerKey = TriggerKey.triggerKey("emailSendingJobTrigger", "hoaxify");
//    final CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//      .forJob(jobDetail)
//      .withIdentity(triggerKey)
//      .withSchedule(
//        CronScheduleBuilder
//          .cronSchedule("* * 2 * * ?")
//          .withMisfireHandlingInstructionFireAndProceed()
//          .inTimeZone(TimeZone.getTimeZone("Europe/Istanbul"))
//      )
//      .build();
//
//    scheduler.scheduleJob(jobDetail, cronTrigger);
  }
}
