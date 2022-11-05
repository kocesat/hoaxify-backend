package com.hoaxify.backend.approval.model.view;

import com.hoaxify.backend.approval.model.Approval;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ApprovalViewModel {
    private Long key;
    private String crudType;
    private String operationGroup;
    private String approvalStatus;
    private LocalDateTime timeOfApprovalB;
    private LocalDateTime timeOfApprovalA;
    private List<ApprovalDetailViewModel> detailList;

    public static ApprovalViewModel newInstance(Approval approval) {
        List<ApprovalDetailViewModel> tempList = approval.getDetailList()
                .stream().map(ApprovalDetailViewModel::newInstance)
                .collect(Collectors.toList());
        return ApprovalViewModel.builder()
                .key(approval.getId())
                .crudType(approval.getCrudType().getText())
                .operationGroup(approval.getOperationGroup().getText())
                .approvalStatus(approval.getStatus().getText())
                .timeOfApprovalB(approval.getTimeOfApprovalB())
                .timeOfApprovalA(approval.getTimeOfApprovalA())
                .detailList(tempList)
                .build();
    }
}
