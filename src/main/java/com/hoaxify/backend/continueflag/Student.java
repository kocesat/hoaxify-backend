package com.hoaxify.backend.continueflag;

import com.hoaxify.backend.common.PersistenceObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "students", schema = "hoaxify")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student extends PersistenceObject {

  @NotNull(message = "name cannot be empty")
  private String name;

  private LocalDateTime created;
}
