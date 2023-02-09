package com.hoaxify.backend.article.service;

import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.article.model.dto.Approvable;

public interface ArticleApprovalService {

    Approval create(Approvable dto);
}
