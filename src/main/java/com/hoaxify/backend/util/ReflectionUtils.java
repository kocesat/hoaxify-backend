package com.hoaxify.backend.util;

import com.hoaxify.backend.common.Approvable;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@UtilityClass
public class ReflectionUtils {

    public static void set(Approvable approvable, String propertyName, String value) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends Approvable> clazz = approvable.getClass();
        Field field = clazz.getDeclaredField(propertyName);
        field.setAccessible(true);
        Class<?> type = field.getType();

        if (type.isAssignableFrom(BigDecimal.class)) {
            field.set(approvable, BigDecimal.valueOf(Long.parseLong(value)));
        } else if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class)) {
            field.set(approvable, Integer.parseInt(value));
        } else if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)) {
            field.set(approvable, Long.parseLong(value));
        } else {
            field.set(approvable, value);
        }
    }
}
