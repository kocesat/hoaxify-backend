package com.hoaxify.backend.approval.enums;

import java.util.Arrays;

public enum ObjectGroup {
    PARAMETERS("P", "Parameters Operations"),
    FEE("F", "Fee Operations"),
    DDB("D", "Direct Debit Operations"),
    ARTICLE("A", "Article Operations");

    private final String code;
    private final String text;

    ObjectGroup(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ObjectGroup getFromCode(String code) {
        return code == null
                ? null
                : Arrays.stream(ObjectGroup.values())
                    .filter(group -> group.code.equals(code))
                    .findFirst()
                    .orElse(null);
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
