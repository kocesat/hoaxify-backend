package com.hoaxify.backend.article.utils;

import com.hoaxify.backend.article.model.dto.Approvable;
import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.model.ArticleCategory;

import java.util.stream.Collectors;

public class ArticleMapper {

    public static Approvable convertToArticleDto(Article articleIn){
        Approvable articleOut = new Approvable();
        articleOut.setTitle(articleIn.getTitle());
        articleOut.setId(articleIn.getId());
        articleOut.setCategoryCode(articleIn.getCategory() == null ? 1 : articleIn.getCategory().getCode());
        articleOut.setCategoryText(articleIn.getCategory() == null ? "Null" : articleIn.getCategory().getText());
        articleOut.setTags(articleIn.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()));
        return articleOut;
    }

    public static Article convertToArticle(Approvable articleIn){
        Article articleOut = new Article();
        articleOut.setTitle(articleIn.getTitle());
        articleOut.setCategory(ArticleCategory.getFromCode(articleIn.getCategoryCode()));
        return articleOut;
    }
}
