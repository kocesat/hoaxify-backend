package com.hoaxify.backend.hexagonal.domain;

public interface PaymentPort {

  boolean makePayment(Payment payment);
}
