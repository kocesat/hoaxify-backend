package com.hoaxify.backend.hexagonal.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentFacade {

  private final PaymentBusiness paymentBusiness;

  public boolean makePayment(Payment payment) {
    return paymentBusiness.create(payment);
  }

}
