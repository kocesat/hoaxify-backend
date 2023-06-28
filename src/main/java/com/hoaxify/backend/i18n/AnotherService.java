package com.hoaxify.backend.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Qualifier("xAnother")
public class AnotherService {

  public String callLog(String messsage) {
    log.info(messsage);
    return messsage;
  }
}
