package com.hoaxify.backend.library.enums;

import java.util.Arrays;

public enum Role {
    SYSTEM_ADMIN(1, "Sistem Yöneticisi"),
    PARTICIPANT(2, "Katılımcı"),
    CA_ADMIN(3, "Sertifia Yöneticisi"),
    PARTICIPANT_ADMIN(4, "Katılımcı Yöneticisi");

    private int code;
    private String value;

    Role(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Role getFromCode(int code) {
        return Arrays.stream(Role.values())
                .filter(role -> role.code == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
