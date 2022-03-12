package com.hoaxify.hoaxifybackend.article.controller;

import com.hoaxify.hoaxifybackend.article.dto.ArticleDto;
import com.hoaxify.hoaxifybackend.article.model.Article;
import com.hoaxify.hoaxifybackend.article.model.ArticleCategory;
import com.hoaxify.hoaxifybackend.article.repository.ArticleRepository;
import com.hoaxify.hoaxifybackend.article.utils.ArticleMapper;
import com.hoaxify.hoaxifybackend.common.ApiSuccess;
import com.hoaxify.hoaxifybackend.common.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = AppConstants.ARTICLECONTROLLER_BASE_PATH, produces = "application/json")
public class ArticleController {
    /*
    Normally Repository class won't be accessed directly, a Service layer should be added
     */

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity findAll() {
        List<ArticleDto> articleDtos = articleRepository.findAll()
                .stream()
                .map(article -> ArticleMapper.convertToArticleDto(article))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiSuccess(200, "Successfull", AppConstants.ARTICLECONTROLLER_BASE_PATH, articleDtos));
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody ArticleDto articleDto) {
        Article article = ArticleMapper.convertToArticle(articleDto);
        articleRepository.save(article);
        ArticleDto savedArticleDto = ArticleMapper.convertToArticleDto(article);
        return ResponseEntity.ok(new ApiSuccess(201, "Created", AppConstants.ARTICLECONTROLLER_BASE_PATH, savedArticleDto));
    }

    @GetMapping("/category")
    public ResponseEntity getAllCategories(){
        Map<Integer, String> categoryMap = ArticleCategory.getAllCategories();
        return ResponseEntity.ok(new ApiSuccess(200, "", AppConstants.ARTICLECONTROLLER_BASE_PATH, categoryMap));
    }
}
