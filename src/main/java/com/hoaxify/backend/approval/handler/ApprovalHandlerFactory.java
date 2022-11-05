package com.hoaxify.backend.approval.handler;

import com.hoaxify.backend.approval.enums.OperationGroup;
import com.hoaxify.backend.article.approval.handler.ArticleApprovalHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApprovalHandlerFactory {

    private final ArticleApprovalHandler articleApprovalHandler;

    public ApprovalHandler getHandler(OperationGroup group) {
        switch (group) {
            case ARTICLE:
                return articleApprovalHandler;
            default:
                return null;
        }
    }
}
