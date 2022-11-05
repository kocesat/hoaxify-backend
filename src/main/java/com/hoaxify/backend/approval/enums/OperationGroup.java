package com.hoaxify.backend.approval.enums;

import java.util.Arrays;

public enum OperationGroup {
    PARAMETERS("P", "Parameters Operations"),
    FEE("F", "Fee Operations"),
    DDB("D", "Direct Debit Operations"),
    ARTICLE("A", "Article Operations");

    private final String code;
    private final String text;

    OperationGroup(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static OperationGroup getFromCode(String code) {
        return code == null
                ? null
                : Arrays.stream(OperationGroup.values())
                    .filter(crudType -> crudType.code.equals(code))
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
