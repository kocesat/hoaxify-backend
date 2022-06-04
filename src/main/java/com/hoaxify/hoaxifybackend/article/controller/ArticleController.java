package com.hoaxify.hoaxifybackend.article.controller;

import com.hoaxify.hoaxifybackend.article.dto.ArticleDto;
import com.hoaxify.hoaxifybackend.article.model.Article;
import com.hoaxify.hoaxifybackend.article.model.ArticleCategory;
import com.hoaxify.hoaxifybackend.article.model.Tag;
import com.hoaxify.hoaxifybackend.article.service.ArticleService;
import com.hoaxify.hoaxifybackend.article.utils.ArticleMapper;
import com.hoaxify.hoaxifybackend.common.ApiSuccess;
import com.hoaxify.hoaxifybackend.common.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = AppConstants.ARTICLECONTROLLER_BASE_PATH, produces = "application/json")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity findAll() {
        List<ArticleDto> articleDtos = articleService.getAll()
                .stream()
                .map(article -> ArticleMapper.convertToArticleDto(article))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiSuccess(200, "Successfull", AppConstants.ARTICLECONTROLLER_BASE_PATH, articleDtos));
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody ArticleDto articleDto) {
        Article article = ArticleMapper.convertToArticle(articleDto);
        Tag tag = new Tag();
        tag.setName("tıraş3");

        article.getTags().add(tag);
        Article articleSaved = articleService.create(article);
        return ResponseEntity.ok(new ApiSuccess(201, "Created", AppConstants.ARTICLECONTROLLER_BASE_PATH, articleSaved));
    }

    @GetMapping("/category")
    public ResponseEntity getAllCategories(){
        Map<Integer, String> categoryMap = ArticleCategory.getAllCategories();
        return ResponseEntity.ok(new ApiSuccess(200, "", AppConstants.ARTICLECONTROLLER_BASE_PATH, categoryMap));
    }
}
