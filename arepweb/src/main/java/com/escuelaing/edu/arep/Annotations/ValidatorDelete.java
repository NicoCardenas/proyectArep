package com.escuelaing.edu.arep.Annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.escuelaing.edu.arep.Annotations.Annotations.Delete;;

/**
 * ValidatorGet
 */
public class ValidatorDelete implements ConstraintValidator<Delete, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }

}