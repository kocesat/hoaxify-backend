package com.hoaxify.backend.article.model.dto;

import com.hoaxify.backend.approval.enums.OperationGroup;
import com.hoaxify.backend.common.Approvable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticleDto implements Approvable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private int categoryCode;

    private String categoryText;
    private List<String> tags = new ArrayList<>();


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public OperationGroup getOperationGroup() {
        return OperationGroup.ARTICLE;
    }
}
