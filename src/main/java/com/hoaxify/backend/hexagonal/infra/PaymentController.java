package com.hoaxify.backend.hexagonal.infra;

import com.hoaxify.backend.hexagonal.domain.Payment;
import com.hoaxify.backend.hexagonal.domain.PaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hexagonal/payment")
public class PaymentController {

  private final PaymentFacade paymentFacade;

  @PostMapping("/make")
  public ResponseEntity<Boolean> makePayment() {
    Payment payment = Payment.builder().build();
    boolean result = paymentFacade.makePayment(payment);
    return ResponseEntity.ok(result);
  }

}
