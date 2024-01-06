package com.hoaxify.backend.hexagonal.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@DomainComponent
@Slf4j
public class PaymentBusiness {

  private final PaymentPort paymentPort;

  public boolean create(Payment payment) {
    return paymentPort.makePayment(payment);
  }

}
