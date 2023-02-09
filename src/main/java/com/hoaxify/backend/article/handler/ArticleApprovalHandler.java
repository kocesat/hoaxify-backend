package com.hoaxify.backend.article.handler;

import com.hoaxify.backend.approval.enums.ApprovalStatus;
import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.handler.ApprovalHandler;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.article.model.dto.Approvable;
import com.hoaxify.backend.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Log4j2
@RequiredArgsConstructor
public class ArticleApprovalHandler implements ApprovalHandler {

    private final ArticleService articleService;

    @Override
    public void handle(Approval approval) throws ApprovalException {
        if (approval.getCrudType() == CrudType.CREATE
                && approval.getStatus() == ApprovalStatus.APPROVED_A) {
            Approvable dto = new Approvable();
            try {
                setObjectFields(dto, approval.getDetailList());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new ApprovalException("Error when creating object", e);
            }
            articleService.create(dto);
        }
    }
}
