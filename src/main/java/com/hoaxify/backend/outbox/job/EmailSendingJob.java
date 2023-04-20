package com.hoaxify.backend.outbox.job;

import com.hoaxify.backend.outbox.enums.MailEventStatus;
import com.hoaxify.backend.outbox.model.MailEvent;
import com.hoaxify.backend.outbox.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@DisallowConcurrentExecution
public class EmailSendingJob extends QuartzJobBean {
  private final AnnouncementService service;
  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    try {
      log.info("Starting EmailSendingJob...");
      LocalDateTime creationTimeStart = LocalDate.now().minusDays(2).atStartOfDay();
      List<MailEventStatus> unsentStatuses = List.of(
        MailEventStatus.INITIAL,
        MailEventStatus.ERROR
      );
      List<MailEvent> mailEvents = service.getMailEvents(unsentStatuses, creationTimeStart);
      for (MailEvent event: mailEvents) {
        tryCompleteEvent(event);
      }
    } catch (Exception e) {
      log.error("Unexpected error running EmailSendingJob....", e);
    } finally {
      log.info("Finished EmailSendingJob...");
    }
  }

  private void tryCompleteEvent(MailEvent event) {
    try {
      sendMail(event);
      service.updateMailEvent(event.getId(), true);
      log.info("Email event completed by sending to: " + event.getRecipient());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      service.updateMailEvent(event.getId(), false);
    }
  }

  private static void sendMail(MailEvent event) {
    log.info(String.format(
      "Sending announcement with header '%s' to recipient %s",
      event.getAnnouncement().getHeader(),
      event.getRecipient()
    ));
  }
}
