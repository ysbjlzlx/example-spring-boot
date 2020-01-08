package com.anydong.example.springboot.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * IsMobilePhone
 *
 * @author Where
 * @date 2019/10/29
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsCountryCallingCodeValidator.class)
public @interface IsCountryCallingCode {
    String message() default "错误的国际电话区号";

    String[] locales() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
