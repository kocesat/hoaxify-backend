package com.hoaxify.backend.approval.model;

import com.hoaxify.backend.approval.enums.ApprovalStatus;
import com.hoaxify.backend.approval.enums.CrudType;
import com.hoaxify.backend.approval.enums.OperationGroup;
import com.hoaxify.backend.approval.enums.converter.AprrovalStatusConverter;
import com.hoaxify.backend.approval.enums.converter.CrudTypeConverter;
import com.hoaxify.backend.approval.enums.converter.OperationGroupConverter;
import com.hoaxify.backend.common.PersistenceObject;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tapproval", schema = "hoaxify")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Approval extends PersistenceObject {

    @Convert(converter = CrudTypeConverter.class)
    private CrudType crudType;

    @Convert(converter = OperationGroupConverter.class)
    private OperationGroup operationGroup;

    @Convert(converter = AprrovalStatusConverter.class)
    @Builder.Default
    private ApprovalStatus status = ApprovalStatus.NEW;

    private LocalDateTime timeOfApprovalB;

    private LocalDateTime timeOfApprovalA;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApprovalDetail> detailList = new ArrayList<>();
}















