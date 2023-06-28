package com.hoaxify.backend.i18n;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

  @Qualifier("xAnother")
  private final AnotherService anotherService;

  public String getMessage() {
    anotherService.callLog("AnotherService called!");
    Locale locale = LocaleContextHolder.getLocale();
    return MessageAccessor.getMessage("DEFAULT", locale);
  }

}
