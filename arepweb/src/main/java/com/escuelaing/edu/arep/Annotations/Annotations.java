package com.escuelaing.edu.arep.Annotations;

import java.lang.annotation.*;
import javax.validation.*;

/**
 * Annotations
 */
public class Annotations {

    @Constraint(validatedBy = ValidatorController.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Controller {
    }

    @Constraint(validatedBy = ValidatorGet.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Get {
        String value();
    }

    @Constraint(validatedBy = ValidatorPost.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Post {
        String value();
    }

    @Constraint(validatedBy = ValidatorPut.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Put {
        String value();
    }

    @Constraint(validatedBy = ValidatorDelete.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Delete {
        String value();
    }
    
}
