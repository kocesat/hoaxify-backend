package com.hoaxify.backend.approval.service;

import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.dto.ApprovalDto;

import java.util.List;

public interface ApprovalService {

    List<Approval> getAllPendings();

    Approval save(Approval approval);

    List<Approval> bulkUpdate(List<ApprovalDto> approvalDtos) throws ApprovalException;
}
