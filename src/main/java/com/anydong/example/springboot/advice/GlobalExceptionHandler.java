package com.anydong.example.springboot.advice;

import com.anydong.example.springboot.Bean.ValidatedResult;
import com.anydong.example.springboot.model.dto.CustomResponseDto;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomResponseDto bindExceptionHandler(BindException e) {
        ValidatedResult validatedResult = new ValidatedResult(false);
        for (FieldError error : e.getFieldErrors()) {
            validatedResult.addError(error.getField(), error.getDefaultMessage());
        }
        CustomResponseDto customResponseDTO = new CustomResponseDto(100422, "");
        customResponseDTO.setData(validatedResult.getErrors());
        return customResponseDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomResponseDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ValidatedResult validatedResult = new ValidatedResult(false);
        for (FieldError error : e.getFieldErrors()) {
            validatedResult.addError(error.getField(), error.getDefaultMessage());
        }
        CustomResponseDto customResponseDTO = new CustomResponseDto(100422, "");
        customResponseDTO.setData(validatedResult.getErrors());
        return customResponseDTO;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomResponseDto httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        ValidatedResult validatedResult = new ValidatedResult(false);
        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) e.getCause();
            List<JsonMappingException.Reference> references = invalidFormatException.getPath();
            for (JsonMappingException.Reference reference : references) {
                validatedResult.addError(reference.getFieldName(), invalidFormatException.getOriginalMessage());
            }
        }
        CustomResponseDto customResponseDTO = new CustomResponseDto(100422, "");
        customResponseDTO.setData(validatedResult.getErrors());
        return customResponseDTO;
    }

}
