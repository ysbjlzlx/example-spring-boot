package com.anydong.example.springboot.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * IsMobilePhoneValidator
 *
 * @author Where
 * @date 2019/10/29
 * <p>
 * 参看：https://github.com/validatorjs/validator.js/blob/master/src/lib/isMobilePhone.js
 * </p>
 */
@Slf4j
public class IsCountryCallingCodeValidator implements ConstraintValidator<IsCountryCallingCode, String> {
    private final Map<String, String> phonePatternMap = new HashMap<>();
    private String[] locales;

    @Override
    public void initialize(IsCountryCallingCode constraintAnnotation) {
        phonePatternMap.put("zh-CN", "(\\+|00)?86");
        phonePatternMap.put("zh-HK", "^(\\+?852$");
        locales = constraintAnnotation.locales();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (String locale : locales) {
            String pattern = phonePatternMap.get(locale);
            if (s!=null && pattern!=null && Pattern.matches(pattern, s)) {
                return true;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("错误的国际电话区号");
        }
        return false;
    }
}
