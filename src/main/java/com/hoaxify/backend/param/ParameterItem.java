package com.hoaxify.backend.param;

import com.hoaxify.backend.common.PersistenceObject;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tparameter", schema = "hoaxify")
public class ParameterItem extends PersistenceObject {

  private String section;
  private String name;
  private String value;

  public ParameterItem() {
  }

  public ParameterItem(String section, String name, String value) {
    this.section = section;
    this.name = name;
    this.value = value;
  }
}
