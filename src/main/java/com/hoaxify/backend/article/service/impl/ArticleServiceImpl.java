package com.hoaxify.backend.article.service.impl;

import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.model.dto.ArticleDto;
import com.hoaxify.backend.article.repository.ArticleRepository;
import com.hoaxify.backend.article.service.ArticleService;
import com.hoaxify.backend.article.utils.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article get(int id) {
        return articleRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Article create(ArticleDto dto) {
        return articleRepository.save(ArticleMapper.convertToArticle(dto));
    }
}
