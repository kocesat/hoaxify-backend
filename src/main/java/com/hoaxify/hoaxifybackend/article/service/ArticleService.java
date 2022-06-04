package com.hoaxify.hoaxifybackend.article.service;

import com.hoaxify.hoaxifybackend.article.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();
    Article get(int id);
    Article create(Article article);
}
