package com.escuelaing.edu.arep.Annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.escuelaing.edu.arep.Annotations.Annotations.Put;

/**
 * ValidatorGet
 */
public class ValidatorPut implements ConstraintValidator<Put, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }

}