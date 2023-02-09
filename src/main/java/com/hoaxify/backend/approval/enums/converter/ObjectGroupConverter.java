package com.hoaxify.backend.approval.enums.converter;

import com.hoaxify.backend.approval.enums.ObjectGroup;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ObjectGroupConverter implements AttributeConverter<ObjectGroup, String> {
    @Override
    public String convertToDatabaseColumn(ObjectGroup attribute) {
        return attribute.getCode();
    }

    @Override
    public ObjectGroup convertToEntityAttribute(String s) {
        return ObjectGroup.getFromCode(s);
    }
}
