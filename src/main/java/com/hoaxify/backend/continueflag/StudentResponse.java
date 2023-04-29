package com.hoaxify.backend.continueflag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
  @Getter(AccessLevel.NONE)
  private boolean hasMore;
  List<Student> students;

  @JsonProperty("devamFlag")
  public boolean hasMore(){
    return hasMore;
  }
}
