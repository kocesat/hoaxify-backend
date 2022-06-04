package com.hoaxify.hoaxifybackend.article.utils;

import com.hoaxify.hoaxifybackend.article.dto.ArticleDto;
import com.hoaxify.hoaxifybackend.article.model.Article;
import com.hoaxify.hoaxifybackend.article.model.ArticleCategory;

import java.util.stream.Collectors;

public class ArticleMapper {

    public static ArticleDto convertToArticleDto(Article articleIn){
        ArticleDto articleOut = new ArticleDto();
        articleOut.setTitle(articleIn.getTitle());
        articleOut.setCategoryCode(articleIn.getCategory().getCode());
        articleOut.setCategoryText(articleIn.getCategory().getText());
        articleOut.setTags(articleIn.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()));
        return articleOut;
    }

    public static Article convertToArticle(ArticleDto articleIn){
        Article articleOut = new Article();
        articleOut.setTitle(articleIn.getTitle());
        articleOut.setCategory(ArticleCategory.getFromCode(articleIn.getCategoryCode()));
        return articleOut;
    }
}
