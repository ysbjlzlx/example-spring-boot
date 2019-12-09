package com.anydong.example.springboot.domain.converter;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.domain.UserDO;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author where
 */
@Converter(autoApply = true)
public class MobilePhoneConverter implements AttributeConverter<UserDO.MobilePhone, String> {

    @Override
    public String convertToDatabaseColumn(UserDO.MobilePhone mobilePhone) {
        return JSON.toJSONString(mobilePhone);
    }

    @Override
    public UserDO.MobilePhone convertToEntityAttribute(String s) {
        return JSON.parseObject(s, UserDO.MobilePhone.class);
    }
}
