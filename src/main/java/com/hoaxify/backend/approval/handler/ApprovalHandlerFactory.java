package com.hoaxify.backend.approval.handler;

import com.hoaxify.backend.approval.enums.ObjectGroup;
import com.hoaxify.backend.article.handler.ArticleApprovalHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApprovalHandlerFactory {

    private final ArticleApprovalHandler articleApprovalHandler;

    public ApprovalHandler getHandler(ObjectGroup group) {
        switch (group) {
            case ARTICLE:
                return articleApprovalHandler;
            default:
                return null;
        }
    }
}
