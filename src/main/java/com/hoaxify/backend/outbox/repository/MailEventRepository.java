package com.hoaxify.backend.outbox.repository;

import com.hoaxify.backend.outbox.model.MailEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MailEventRepository extends JpaRepository<MailEvent, Long> {

  @Query(
    "select me from MailEvent me join fetch me.announcement " +
    "where me.sentStatus in :sentStatusList and me.created >= :createTime "
  )
  List<MailEvent> findBySentStatusAndCreatedAfter(List<Integer> sentStatusList, LocalDateTime createTime);

  @Modifying
  @Query(value =
    "update MailEvent me set me.sentStatus = 1, me.sentTime = :sentTime " +
    "where me.id = :id")
  void updateStatusAsSent(Long id, LocalDateTime sentTime);

  @Modifying
  @Query(value =
    "update MailEvent me set me.sentStatus = 3 " +
    "where me.id = :id")
  void updateStatusAsError(Long id);
}
