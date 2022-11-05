package com.hoaxify.backend.article.service.impl;

import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.ApprovalDetail;
import com.hoaxify.backend.approval.service.ApprovalService;
import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.repository.ArticleRepository;
import com.hoaxify.backend.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ApprovalService approvalService;

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

    @Override
    public Approval createWithApproval(Article article) {
        List<ApprovalDetail> approvalDetails = new ArrayList<>();
        ApprovalDetail approvalDetail = new ApprovalDetail("title", article.getTitle());
        approvalDetails.add(approvalDetail);
        Approval approval = Approval.builder()
            .crudType(CrudType.CREATE)
            .detailList(approvalDetails)
            .operationGroup(article.getOperationGroup())
            .build();
        return approvalService.save(approval);
    }
}
