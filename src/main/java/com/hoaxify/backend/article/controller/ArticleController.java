package com.hoaxify.backend.article.controller;

import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.view.ApprovalViewModel;
import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.model.ArticleCategory;
import com.hoaxify.backend.article.model.dto.Approvable;
import com.hoaxify.backend.article.projection.CategoryCount;
import com.hoaxify.backend.article.repository.ArticleRepository;
import com.hoaxify.backend.article.service.ArticleApprovalService;
import com.hoaxify.backend.article.service.ArticleService;
import com.hoaxify.backend.article.utils.ArticleMapper;
import com.hoaxify.backend.common.ApiSuccess;
import com.hoaxify.backend.common.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = AppConstants.ARTICLECONTROLLER_BASE_PATH, produces = "application/json")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleRepository repository;
    private final ArticleApprovalService articleApprovalService;

    @GetMapping("/count")
    public ResponseEntity fingCategorySummary() {
        List<CategoryCount> categoryCount = repository.findCategoryCounts();
        return ResponseEntity.ok(categoryCount);
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Approvable> articleDtos = articleService.getAll()
                .stream()
                .map(ArticleMapper::convertToArticleDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiSuccess(200, "Successfull", AppConstants.ARTICLECONTROLLER_BASE_PATH, articleDtos));
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Approvable articleDto) {
        Article articleSaved = articleService.create(articleDto);
        return ResponseEntity.ok(new ApiSuccess(201, "Created", AppConstants.ARTICLECONTROLLER_BASE_PATH, articleSaved));
    }

    @PostMapping("request/add")
    public ResponseEntity createWithApproval(@RequestBody Approvable articleDto) {
        Approval approval = articleApprovalService.create(articleDto);
        return ResponseEntity.ok(new ApiSuccess(200, "B Onayına gönderildi", AppConstants.ARTICLECONTROLLER_BASE_PATH,
                ApprovalViewModel.newInstance(approval)));
    }

    @GetMapping("/category")
    public ResponseEntity getAllCategories(){
        Map<Integer, String> categoryMap = ArticleCategory.getAllCategories();
        return ResponseEntity.ok(new ApiSuccess(200, "", AppConstants.ARTICLECONTROLLER_BASE_PATH, categoryMap));
    }
}
