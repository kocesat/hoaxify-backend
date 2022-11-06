package com.hoaxify.backend.approval.handler;

import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.ApprovalDetail;
import com.hoaxify.backend.common.Approvable;
import com.hoaxify.backend.util.ReflectionUtils;

import java.util.List;

public interface ApprovalHandler {

    void handle(Approval approval) throws ApprovalException;

    default Approvable setObjectFields(Approvable approvable, List<ApprovalDetail> details) throws ApprovalException, IllegalAccessException, NoSuchFieldException {
        for (ApprovalDetail detail : details) {
            ReflectionUtils.set(approvable, detail.getPropertyName(), detail.getValue());
        }
        return approvable;
    }

}
