package com.anydong.example.springboot.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class IsMobilePhoneValidator implements ConstraintValidator<IsMobilePhone, String> {
    private final Logger logger = LoggerFactory.getLogger(IsMobilePhoneValidator.class);
    private Map<String, String> phonePatternMap = new HashMap<>();
    private String[] locales;

    @Override
    public void initialize(IsMobilePhone constraintAnnotation) {
        phonePatternMap.put("zh-CN", "^((\\+|00)?86)?1([358][0-9]|4[579]|6[67]|7[01235678]|9[189])[0-9]{8}$");
        phonePatternMap.put("zh-HK", "^(\\+?852\\-?)?[456789]\\d{3}\\-?\\d{4}$");
        locales = constraintAnnotation.locales();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (String locale : locales) {
            String pattern = phonePatternMap.get(locale);
            if (s != null && pattern != null && Pattern.matches(pattern, s)) {
                return true;
            }
        }
        return false;
    }
}
