package com.anydong.example.springboot.domain.converter;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.domain.UserDo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author where
 */
@Converter(autoApply = true)
public class MobilePhoneConverter implements AttributeConverter<UserDo.MobilePhone, String> {

    @Override
    public String convertToDatabaseColumn(UserDo.MobilePhone mobilePhone) {
        return JSON.toJSONString(mobilePhone);
    }

    @Override
    public UserDo.MobilePhone convertToEntityAttribute(String s) {
        return JSON.parseObject(s, UserDo.MobilePhone.class);
    }
}
