package com.escuelaing.edu.arep.Annotations;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.escuelaing.edu.arep.Annotations.Annotations.Web;

public class ValidatorWeb implements ConstraintValidator<Web, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }

    public void initializeWeb() {
        Reflections reflections = new Reflections("com.escuelaing.edu.arep.Server", new SubTypesScanner(false));
    
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        for (Class<?> c : allClasses) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Web.class)) {
                    URLHandler.put("apps/" + m.getAnnotation(Web.class).value(), new StaticMethodHandler(m));
                }
            }
        }
    }
}