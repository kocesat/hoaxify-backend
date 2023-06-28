package com.hoaxify.backend.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class FraudService {

  public <T> T checkForFraud(T request, FraudSensitiveService<T> fraudSensitiveService) {
    log.info("FraudService checking for fraud...Either call the real service or return immediately");
    log.info("Request Object: " + request);
    return fraudSensitiveService.handlePostFraudCheck(request);
  }
}
