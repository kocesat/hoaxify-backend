package com.hoaxify.backend.article.service;

import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.model.dto.Approvable;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();
    Article get(int id);
    Article create(Approvable dto);
}
