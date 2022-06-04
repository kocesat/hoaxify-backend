package com.hoaxify.hoaxifybackend.article.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDto {

    @NotNull
    private String title;

    @NotNull
    private int categoryCode;

    private String categoryText;
    private List<String> tags = new ArrayList<>();
}
