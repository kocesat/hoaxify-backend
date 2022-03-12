package com.hoaxify.hoaxifybackend.article.repository;

import com.hoaxify.hoaxifybackend.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
