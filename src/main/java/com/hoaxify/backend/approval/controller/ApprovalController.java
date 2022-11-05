package com.hoaxify.backend.approval.controller;

import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.model.dto.ApprovalDto;
import com.hoaxify.backend.approval.model.view.ApprovalViewModel;
import com.hoaxify.backend.approval.service.ApprovalService;
import com.hoaxify.backend.common.AppConstants;
import com.hoaxify.backend.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = AppConstants.APPROVAL_CONTROLLER_BASE_PATH, produces = "application/json")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllPendingApproval() {
        List<ApprovalViewModel> approvals = approvalService.getAllPendings()
                .stream()
                .map(ApprovalViewModel::newInstance)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.success(approvals, null));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> bulkUpdate(@RequestBody @Valid List<ApprovalDto> dtos) throws ApprovalException {
        List<ApprovalViewModel> approvals = approvalService.bulkUpdate(dtos)
                .stream()
                .map(ApprovalViewModel::newInstance)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.success(approvals, null));
    }

}
