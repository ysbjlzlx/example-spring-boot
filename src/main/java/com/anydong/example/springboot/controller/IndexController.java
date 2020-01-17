package com.anydong.example.springboot.controller;

import com.anydong.example.springboot.model.dto.CustomResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Where
 */
@Controller
public class IndexController {
    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(value = "locale", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CustomResponseDto locale() {
        Map<String, String> data = new HashMap<>();
        data.put("locale.Locale", LocaleContextHolder.getLocale().toString());
        data.put("locale.Timezone", LocaleContextHolder.getTimeZone().toString());
        data.put("locale", Locale.getDefault().toString());
        data.put("timezone", TimeZone.getDefault().toString());
        return new CustomResponseDto(100000, messageSource.getMessage("welcome", null, LocaleContextHolder.getLocale()), data);
    }
}
