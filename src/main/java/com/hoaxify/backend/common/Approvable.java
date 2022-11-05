package com.hoaxify.backend.common;

import com.hoaxify.backend.approval.enums.OperationGroup;

public interface Approvable {

    Long getId();

    OperationGroup getOperationGroup();
}
