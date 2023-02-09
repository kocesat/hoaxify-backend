package com.hoaxify.backend.approval.service;

import com.hoaxify.backend.approval.enums.ApprovalStatus;
import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.dto.ApprovalDto;
import com.hoaxify.backend.article.model.dto.Approvable;

import java.util.List;
import java.util.Map;

public interface ApprovalService {

    List<Approval> getAllPendings();

    List<Approval> bulkUpdate(List<ApprovalDto> approvalDtos) throws ApprovalException;

    Approval create(Approvable approvable, CrudType crudType, Map<String, String> newValues, ApprovalStatus approveMode);
}
