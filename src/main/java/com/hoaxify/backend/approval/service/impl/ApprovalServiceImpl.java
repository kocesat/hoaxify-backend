package com.hoaxify.backend.approval.service.impl;

import com.hoaxify.backend.approval.enums.ApprovalStatus;
import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.handler.ApprovalHandler;
import com.hoaxify.backend.approval.handler.ApprovalHandlerFactory;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.ApprovalDetail;
import com.hoaxify.backend.approval.model.dto.ApprovalDto;
import com.hoaxify.backend.approval.repository.ApprovalDetailRepository;
import com.hoaxify.backend.approval.repository.ApprovalRepository;
import com.hoaxify.backend.approval.service.ApprovalService;
import com.hoaxify.backend.article.model.dto.Approvable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository repository;

    private final ApprovalDetailRepository detailRepository;

    private final ApprovalHandlerFactory handlerFactory;

    @Override
    public List<Approval> getAllPendings() {
        return repository.findByStatusIn(
                List.of(ApprovalStatus.APPROVED_B, ApprovalStatus.NEW)
        );
    }

    @Override
    public List<Approval> bulkUpdate(List<ApprovalDto> approvalDtos) throws ApprovalException {
        List<Approval> result = new ArrayList<>();
        for (ApprovalDto dto : approvalDtos) {
            Approval approval = repository.findById(dto.getId())
                    .orElseThrow(ApprovalException::new);
            result.add(approval);
            ApprovalStatus approvalStatus = ApprovalStatus.getFromCode(dto.getApproveMode());
            switch (approvalStatus) {
                case APPROVED_B:
                    this.approveB(approval);
                    break;
                case APPROVED_A:
                    this.approveA(approval);
                    break;
                case REJECTED:
                    approval.setStatus(ApprovalStatus.REJECTED);
                    repository.save(approval);
                    break;
                default:
                    throw new IllegalStateException("Unsupported approval type!");
            }
        }
        return result;
    }

    @Override
    public Approval create(Approvable approvable, CrudType crudType, Map<String, String> newValues, ApprovalStatus approvalStatus) {
        Approval approval = Approval.builder()
                .crudType(crudType)
                .objectGroup(approvable.getObjectGroup())
                .status(approvalStatus)
                .build();
        /**
         * TODO:
         * Also create a representational string of approval
         * like what will be done when this approval gets approvedA finally
         * for example: An article whose name is blabla will be created!
         */
        repository.save(approval);
        List<ApprovalDetail> details = new ArrayList<>();
        for (String propertyName : newValues.keySet()) {
            details.add(new ApprovalDetail(propertyName, newValues.get(propertyName), approval));
        }
        approval.setDetailList(detailRepository.saveAll(details));
        return approval;
    }

    private void approveA(Approval approval) throws ApprovalException {
        if (approval.getStatus() != ApprovalStatus.APPROVED_B) {
            throw new ApprovalException("Cannot approve A");
        }
        // todo: check user authorization. i.e does user have the right to approve B?
        // todo: check user is not already in the approval chain
        approval.setStatus(ApprovalStatus.APPROVED_A);
        approval.setTimeOfApprovalA(LocalDateTime.now());
        repository.save(approval);
        ApprovalHandler handler = handlerFactory.getHandler(approval.getObjectGroup());
        handler.handle(approval);
    }

    private void approveB(Approval approval) throws ApprovalException {
        if (approval.getStatus() != ApprovalStatus.NEW) {
            throw new ApprovalException("Approval status must be NEW to approve B");
        }
        // todo: check user authorization. i.e does user have the right to approve B?
        // todo: check user is not already in the approval chain
        approval.setStatus(ApprovalStatus.APPROVED_B);
        approval.setTimeOfApprovalB(LocalDateTime.now());
        repository.save(approval);
    }
}
