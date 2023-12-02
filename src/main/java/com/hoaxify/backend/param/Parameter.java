package com.hoaxify.backend.param;

import com.hoaxify.backend.common.PersistenceObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "param", schema = "hoaxify")
public class Parameter extends PersistenceObject {

  private String section;
  private String name;
  private String value;
}
