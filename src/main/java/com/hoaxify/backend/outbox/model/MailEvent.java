package com.hoaxify.backend.outbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tmail_event", schema = "hoaxify")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "announcement_id", nullable = false)
  private Announcement announcement;
  private String recipient;
  private LocalDateTime created;
  private int sentStatus; // 0: initial, 1: sent, 2: error
  private LocalDateTime sentTime;
}
