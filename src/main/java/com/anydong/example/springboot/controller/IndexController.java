package com.anydong.example.springboot.controller;

import com.anydong.example.springboot.dto.CustomResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Where
 */
@RestController
@RequestMapping(produces = "application/json; charset=utf-8")
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public CustomResponseDTO index() {
        return new CustomResponseDTO(100000, "Hello World");
    }
}
