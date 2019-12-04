package com.anydong.example.springboot.exception;


import com.anydong.example.springboot.model.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Where
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomResponse bindExceptionHandler(BindException e) {
        Map<String, List> errors = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            if (errors.containsKey(error.getField())) {
                List<String> errorMessageList = errors.get(error.getField());
                errorMessageList.add(error.getDefaultMessage());
                errors.put(error.getField(), errorMessageList);
            } else {
                List<String> errorMessageList = new ArrayList<>();
                errorMessageList.add(error.getDefaultMessage());
                errors.put(error.getField(), errorMessageList);
            }
        }
        CustomResponse customResponse = new CustomResponse(100422, "");
        customResponse.setData(errors);
        return customResponse;
    }
}
