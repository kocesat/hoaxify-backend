package com.hoaxify.hoaxifybackend.article.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleDto {

    @NotNull
    private String title;

    @NotNull
    private int categoryCode;

    private String categoryText;
}
