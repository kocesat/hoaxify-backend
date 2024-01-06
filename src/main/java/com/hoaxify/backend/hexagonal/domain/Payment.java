package com.hoaxify.backend.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

  private String fromAccount;
  private String toAccount;
  private BigDecimal amount;
}
