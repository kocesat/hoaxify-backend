package com.hoaxify.backend.outbox.enums;

public enum MailEventStatus {
  INITIAL(0),
  SENT(1),
  ERROR(2);
  private final int code;

  MailEventStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
