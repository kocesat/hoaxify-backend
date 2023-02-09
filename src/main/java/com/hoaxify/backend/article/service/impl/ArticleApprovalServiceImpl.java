package com.hoaxify.backend.article.service.impl;

import com.hoaxify.backend.approval.enums.ApprovalStatus;
import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.service.ApprovalService;
import com.hoaxify.backend.article.model.dto.Approvable;
import com.hoaxify.backend.article.service.ArticleApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ArticleApprovalServiceImpl implements ArticleApprovalService {

    private final ApprovalService approvalService;

    @Override
    public Approval create(Approvable dto) {
        Map<String, String> newValues = new HashMap<>();
        newValues.put("title", dto.getTitle());
        newValues.put("categoryCode", String.valueOf(dto.getCategoryCode()));
        return approvalService.create(dto, CrudType.CREATE, newValues, ApprovalStatus.NEW);
    }
}
