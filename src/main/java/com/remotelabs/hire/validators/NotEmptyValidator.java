package com.remotelabs.hire.validators;

import com.remotelabs.hire.annotations.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyValidator implements ConstraintValidator<NotEmptyList, List<?>> {
    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(List<?> objects, ConstraintValidatorContext constraintValidatorContext) {
        return objects != null && !objects.isEmpty();
    }
}
