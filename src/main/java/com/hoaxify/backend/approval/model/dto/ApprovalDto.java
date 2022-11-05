package com.hoaxify.backend.approval.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApprovalDto {
    @NotNull(message = "Onay numarası boş olamaz")
    private Long id;
    @NotNull(message = "Onay boş olamaz")
    private String approveMode; // B || A || R
}
