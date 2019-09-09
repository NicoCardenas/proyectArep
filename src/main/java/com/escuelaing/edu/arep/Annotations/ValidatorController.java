package com.escuelaing.edu.arep.Annotations;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.escuelaing.edu.arep.Annotations.Annotations.Controller;

public class ValidatorController implements ConstraintValidator<Controller, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }
    
    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Controller.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

}