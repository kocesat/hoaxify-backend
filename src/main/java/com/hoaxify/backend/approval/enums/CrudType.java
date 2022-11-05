package com.hoaxify.backend.approval.enums;

import java.util.Arrays;

public enum CrudType {
    CREATE("C", "Ekleme"),
    READ("R", "Okuma"),
    UPDATE("U", "Güncelleme"),
    DELETE("D", "Sil");

    private final String code;
    private final String text;

    CrudType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static CrudType getFromCode(String code) {
        return code == null
                ? null
                : Arrays.stream(CrudType.values())
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
