package com.anydong.example.springboot.controller;

import com.anydong.example.springboot.dto.CustomResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Where
 */
@RestController
@RequestMapping(produces = "application/json; charset=utf-8")
public class IndexController {
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public CustomResponseDTO index() {
        return new CustomResponseDTO(100000, "Hello World");
    }

    @GetMapping(value = "locale")
    public CustomResponseDTO locale() {
        Map<String, String> data = new HashMap<>();
        data.put("locale.Locale", LocaleContextHolder.getLocale().toString());
        data.put("locale.Timezone", LocaleContextHolder.getTimeZone().toString());
        data.put("locale", Locale.getDefault().toString());
        data.put("timezone", TimeZone.getDefault().toString());
        return new CustomResponseDTO(100000, messageSource.getMessage("welcome", null, LocaleContextHolder.getLocale()), data);
    }
}
