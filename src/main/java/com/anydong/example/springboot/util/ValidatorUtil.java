package com.anydong.example.springboot.util;

import com.anydong.example.springboot.Bean.ValidatedResult;

import javax.validation.*;
import java.util.Set;

public class ValidatorUtil {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidatedResult validate(T bean, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(bean, groups);
        if (constraintViolations.isEmpty()) {
            return new ValidatedResult(true);
        }
        ValidatedResult validateResult = new ValidatedResult(false);
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            String field = constraintViolation.getPropertyPath().toString();
            validateResult.addError(field, constraintViolation.getMessage());
        }
        return validateResult;
    }
}
