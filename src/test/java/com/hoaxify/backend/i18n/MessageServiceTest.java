package com.hoaxify.backend.i18n;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Locale;

/**
 * Example of both mock and autowired
 * stub with Mockito
 */
@SpringBootTest(classes = {MessageAccessor.class})
@ImportAutoConfiguration(MessageSourceAutoConfiguration.class)
@ActiveProfiles("test")
class MessageServiceTest {

  @Autowired
  private MessageAccessor messageAccessor;
  @MockBean
  private AnotherService xAnother;

  private MessageService serviceUnderTest;

  @BeforeEach
  void setup() {
    serviceUnderTest = new MessageService(xAnother);
  }

  @Test
  void test() {
    Mockito.when(xAnother.callLog(Mockito.anyString()))
        .thenReturn("Message...");
    LocaleContextHolder.setLocale(new Locale("tr-TR"));
    System.out.println(serviceUnderTest.getMessage());
  }

}
