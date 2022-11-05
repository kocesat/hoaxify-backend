package com.hoaxify.backend.article.approval.handler;

import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.handler.ApprovalHandler;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@RequiredArgsConstructor
@Log4j2
public class ArticleApprovalHandler implements ApprovalHandler {

    private final ArticleRepository articleRepository;



    @Override
    public void handle(Approval approval) throws ApprovalException {
        if (approval.getCrudType() == CrudType.CREATE) {
            Article article = new Article();
            try {
                setFields(article, approval.getDetailList());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new ApprovalException("Obje yaratılırken hata oluştu", e);
            }
            articleRepository.save(article);
        }
    }
}
