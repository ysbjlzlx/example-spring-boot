package com.anydong.example.springboot.domain.converter;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.domain.User;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author where
 */
@Converter(autoApply = true)
public class MobilePhoneConverter implements AttributeConverter<User.MobilePhone, String> {

    @Override
    public String convertToDatabaseColumn(User.MobilePhone mobilePhone) {
        return JSON.toJSONString(mobilePhone);
    }

    @Override
    public User.MobilePhone convertToEntityAttribute(String s) {
        return JSON.parseObject(s, User.MobilePhone.class);
    }
}
