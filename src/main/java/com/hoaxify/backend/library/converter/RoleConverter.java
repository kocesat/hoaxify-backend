package com.hoaxify.backend.library.converter;

import com.hoaxify.backend.library.enums.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        return Role.getFromCode(dbData);
    }
}
