package com.hoaxify.backend.approval.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hoaxify.backend.common.PersistenceObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tapproval_detail", schema = "hoaxify")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalDetail extends PersistenceObject {

    private String propertyName;

    private String value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_id")
    @JsonBackReference
    private Approval approval;
}
