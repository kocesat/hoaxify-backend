package com.hoaxify.backend.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class FraudSensitiveUserService implements FraudSensitiveService<UserUpdateDto> {

  private final FraudService fraudService;

  public UserUpdateDto updateUser(UserUpdateDto dto) {
    log.info("UserService first call...");
    return fraudService.checkForFraud(dto, this);
  }

  @Override
  public UserUpdateDto handlePostFraudCheck(UserUpdateDto requestObject) {
    log.info("User service handling the request!");
    return requestObject;
  }

}
