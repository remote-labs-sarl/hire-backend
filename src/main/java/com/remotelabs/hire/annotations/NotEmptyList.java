package com.remotelabs.hire.annotations;

import com.remotelabs.hire.validators.NotEmptyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmptyList {

    String message() default "List cannot be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
