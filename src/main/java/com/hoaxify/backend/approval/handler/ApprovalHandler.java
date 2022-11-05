package com.hoaxify.backend.approval.handler;

import com.hoaxify.backend.approval.exception.ApprovalException;
import com.hoaxify.backend.approval.model.Approval;
import com.hoaxify.backend.approval.model.ApprovalDetail;
import com.hoaxify.backend.common.Approvable;

import java.lang.reflect.Field;
import java.util.List;

public interface ApprovalHandler {

    void handle(Approval approval) throws ApprovalException;

    default Approvable setFields(Approvable approvable, List<ApprovalDetail> details) throws ApprovalException, IllegalAccessException, NoSuchFieldException {
        for (ApprovalDetail detail : details) {
            Field field = approvable.getClass().getDeclaredField(detail.getPropertyName());
            field.setAccessible(true);
            // TODO: If field is enum, how to cast string to enum
            field.set(approvable, detail.getValue());
        }
        return approvable;
    }

}
