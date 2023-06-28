package com.hoaxify.backend.i18n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageAccessor {
  private static MessageSource messageSource;

  public MessageAccessor(MessageSource messageSource) {
    MessageAccessor.messageSource = messageSource;
  }

  public static String getMessage(String key, Locale locale) {
    return messageSource.getMessage(key, new Object[0], locale);
  }
}
