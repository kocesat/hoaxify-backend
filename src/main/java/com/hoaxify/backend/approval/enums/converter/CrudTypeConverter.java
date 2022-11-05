package com.hoaxify.backend.approval.enums.converter;

import com.hoaxify.backend.approval.enums.CrudType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CrudTypeConverter implements AttributeConverter<CrudType, String> {
    @Override
    public String convertToDatabaseColumn(CrudType crudType) {
        return crudType.getCode();
    }

    @Override
    public CrudType convertToEntityAttribute(String s) {
        return CrudType.getFromCode(s);
    }
}
