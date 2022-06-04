package com.hoaxify.hoaxifybackend.article.service;

import com.hoaxify.hoaxifybackend.article.model.Article;
import com.hoaxify.hoaxifybackend.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article get(int id) {
        return articleRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Article create(Article article) {
        return articleRepository.save(article);
    }
}
