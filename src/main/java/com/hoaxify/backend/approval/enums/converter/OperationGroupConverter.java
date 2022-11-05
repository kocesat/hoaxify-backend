package com.hoaxify.backend.approval.enums.converter;

import com.hoaxify.backend.approval.enums.OperationGroup;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OperationGroupConverter implements AttributeConverter<OperationGroup, String> {
    @Override
    public String convertToDatabaseColumn(OperationGroup attribute) {
        return attribute.getCode();
    }

    @Override
    public OperationGroup convertToEntityAttribute(String s) {
        return OperationGroup.getFromCode(s);
    }
}
