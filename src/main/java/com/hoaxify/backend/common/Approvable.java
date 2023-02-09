package com.hoaxify.backend.common;

import com.hoaxify.backend.approval.enums.ObjectGroup;

public interface Approvable {
    Long getId();
    ObjectGroup getObjectGroup();
}
