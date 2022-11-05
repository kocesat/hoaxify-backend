package com.hoaxify.backend.approval.enums;

import java.util.Arrays;

public enum ApprovalStatus {
    NEW("N", "New"),
    APPROVED_B("B", "Approved B"),
    APPROVED_A("A", "Approved A"),
    REJECTED("R", "Rejected");

    private final String code;
    private final String text;

    ApprovalStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ApprovalStatus getFromCode(String code) {
        return code == null
                ? null
                : Arrays.stream(ApprovalStatus.values())
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
