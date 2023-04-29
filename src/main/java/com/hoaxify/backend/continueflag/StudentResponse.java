package com.hoaxify.backend.continueflag;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
  private boolean hasMore;
  List<Student> students;

  public boolean hasMore() {
    return hasMore;
  }
}
