package com.hoaxify.backend.article.controller;

import com.hoaxify.backend.article.dto.ArticleDto;
import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.model.ArticleCategory;
import com.hoaxify.backend.article.model.Tag;
import com.hoaxify.backend.article.projection.CategoryCount;
import com.hoaxify.backend.article.repository.ArticleRepository;
import com.hoaxify.backend.article.service.ArticleService;
import com.hoaxify.backend.article.utils.ArticleMapper;
import com.hoaxify.backend.common.ApiSuccess;
import com.hoaxify.backend.common.AppConstants;
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
    private final ArticleRepository repository;

    @GetMapping("/count")
    public ResponseEntity fingCategorySummary() {
        List<CategoryCount> categoryCount = repository.findCategoryCounts();
        return ResponseEntity.ok(categoryCount);
    }

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
