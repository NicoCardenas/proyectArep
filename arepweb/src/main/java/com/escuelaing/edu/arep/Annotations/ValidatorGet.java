package com.escuelaing.edu.arep.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.escuelaing.edu.arep.Annotations.Annotations.Get;

/**
 * ValidatorGet
 */
public class ValidatorGet implements ConstraintValidator<Get, String> {

    private String prefix;

    @Override
    public void initialize(Get constraintAnnotation) {
        // TODO Auto-generated method stub
        this.prefix = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result;
        if (value != null) result = value.startsWith(this.prefix);
        else result = true;
        return result;
    }

    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Get.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private String getGetString(Object object) {
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Get.class)) {
                System.out.println("x");
            }
        }
        return "";
    }

}