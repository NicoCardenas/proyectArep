package com.escuelaing.edu.arep.Annotations;

import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = ValidatorWeb.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Web {
    String value() default "Sample";
}