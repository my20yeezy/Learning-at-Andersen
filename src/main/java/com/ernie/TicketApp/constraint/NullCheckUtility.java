package com.ernie.TicketApp.constraint;

import java.lang.reflect.Field;

public class NullCheckUtility {
    public static void validateNotNull(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(obj) == null) {
                        NullableWarning notNull = field.getAnnotation(NullableWarning.class);
                        System.out.println("Variable [" + field.getName() + "] is null in [" + field.getClass().getSimpleName() + "]!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}