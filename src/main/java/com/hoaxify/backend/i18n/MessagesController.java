package com.hoaxify.backend.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("i18n/message")
@RequiredArgsConstructor
public class MessagesController {

  private final MessageService service;
  @GetMapping
  public String getMessage() {
    return service.getMessage();
  }
}
