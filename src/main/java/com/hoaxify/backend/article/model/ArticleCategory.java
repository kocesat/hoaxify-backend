package com.hoaxify.backend.article.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum ArticleCategory {
    SPORT(1, "S"),
    MUSIC(2, "M"),
    TECHNOLOGY(3, "T");

    private int code;
    private String text;

    ArticleCategory(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static ArticleCategory getFromCode(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(ArticleCategory.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Map<Integer, String> getAllCategories() {
        Map<Integer, String> categoryMap = new HashMap<>();
        Stream.of(ArticleCategory.values()).forEach(c -> {
                    categoryMap.put(c.getCode(), c.getText());
                });
        return categoryMap;
    }
}
