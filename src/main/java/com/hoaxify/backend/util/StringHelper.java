package com.hoaxify.backend.util;

public final class StringHelper {

  public static String toUnicode(char ch) {
    return String.format("\\u%04x", (int) ch);
  }
}
