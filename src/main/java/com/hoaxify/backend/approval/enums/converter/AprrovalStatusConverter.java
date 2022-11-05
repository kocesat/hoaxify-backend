package com.hoaxify.backend.approval.enums.converter;

import com.hoaxify.backend.approval.enums.ApprovalStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AprrovalStatusConverter implements AttributeConverter<ApprovalStatus, String> {
    @Override
    public String convertToDatabaseColumn(ApprovalStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public ApprovalStatus convertToEntityAttribute(String s) {
        return ApprovalStatus.getFromCode(s);
    }
}
