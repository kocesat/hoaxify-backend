package com.hoaxify.backend.outbox.service;

import com.hoaxify.backend.outbox.enums.MailEventStatus;
import com.hoaxify.backend.outbox.model.Announcement;
import com.hoaxify.backend.outbox.model.MailEvent;
import com.hoaxify.backend.outbox.repository.AnnouncementRepository;
import com.hoaxify.backend.outbox.repository.MailEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AnnouncementService {
  private final AnnouncementRepository announcementRepository;
  private final MailEventRepository mailEventRepository;
  private static final String[] recipientList = getMailList();

  public Announcement create(Announcement announcement){
    announcement.setCreated(LocalDateTime.now());
    final Announcement announcementInDb = announcementRepository.save(announcement);
    mailEventRepository.saveAll(createMailEvents(announcementInDb));
    return announcementInDb;
  }

  @Transactional(readOnly = true)
  public List<MailEvent> getMailEvents(List<MailEventStatus> statuses, LocalDateTime creationTimeStart) {
    return mailEventRepository.findBySentStatusAndCreatedAfter(
      statuses.stream()
        .map(s -> s.getCode())
        .collect(Collectors.toList()),
      creationTimeStart);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void updateMailEvent(Long id, boolean isSent) {
    if (isSent) {
      mailEventRepository.updateStatusAsSent(id, LocalDateTime.now());
    } else {
      mailEventRepository.updateStatusAsError(id);
    }
  }

  private static List<MailEvent> createMailEvents(Announcement announcementInDb) {
    List<MailEvent> mailEvents = new ArrayList<>();
    for (String recipient : recipientList) {
      mailEvents.add(MailEvent.builder()
        .created(LocalDateTime.now())
        .announcement(announcementInDb)
        .recipient(recipient)
        .sentStatus(0)
        .build());
    }
    return mailEvents;
  }

  private static String[] getMailList() {
    return new String[]{
      "kocesat@gmail.com",
      "cagri@gmail.com",
      "semih@gmail.com",
      "mertkan@gmail.com"
    };
  }
}

