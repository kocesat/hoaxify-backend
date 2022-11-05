package com.hoaxify.backend.approval.model;

import com.hoaxify.backend.common.PersistenceObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tapproval_detail", schema = "hoaxify")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalDetail extends PersistenceObject {

    private String propertyName;

    private String value;
}
