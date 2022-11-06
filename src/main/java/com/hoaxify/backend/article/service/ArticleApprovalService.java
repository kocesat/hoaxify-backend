package com.hoaxify.backend.article.service;

import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.article.model.dto.ArticleDto;

public interface ArticleApprovalService {

    Approval create(ArticleDto dto);
}
