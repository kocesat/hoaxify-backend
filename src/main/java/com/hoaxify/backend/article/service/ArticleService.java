package com.hoaxify.backend.article.service;

import com.hoaxify.backend.article.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();
    Article get(int id);
    Article create(Article article);
}
