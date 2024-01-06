package com.hoaxify.backend.hexagonal.infra;

import com.hoaxify.backend.hexagonal.domain.Payment;
import com.hoaxify.backend.hexagonal.domain.PaymentPort;
import org.springframework.stereotype.Component;

@Component
public class PaymentAdapter implements PaymentPort {

  @Override
  public boolean makePayment(Payment payment) {
    // fake impl
    return true;
  }
}
