package com.hoaxify.backend.approval.model.view;

import com.hoaxify.backend.approval.model.ApprovalDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApprovalDetailViewModel {
    private String propertyName;
    private String value;

    public static ApprovalDetailViewModel newInstance(ApprovalDetail detail) {
        return ApprovalDetailViewModel.builder()
                .propertyName(detail.getPropertyName())
                .value(detail.getValue())
                .build();
    }
}
