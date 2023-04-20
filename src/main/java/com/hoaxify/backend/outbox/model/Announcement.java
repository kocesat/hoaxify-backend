package com.hoaxify.backend.outbox.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoaxify.backend.common.web.serializer.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "tannouncement", schema = "hoaxify")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "header field cannot be empty")
  private String header;
  @NotEmpty(message = "content field cannot be empty")
  private String content;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime created;
}
