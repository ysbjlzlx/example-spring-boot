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

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     *
     * @param e BindException
     * @return CustomResponseDto
     */
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

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param e ConstraintViolationException
     * @return CustomResponseDto
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomResponseDto constraintViolationExceptionHandler(ConstraintViolationException e) {
        ValidatedResult validatedResult = new ValidatedResult(false);
        e.getConstraintViolations().forEach(constraintViolation -> {
            String field = constraintViolation.getPropertyPath().toString();
            validatedResult.addError(field, constraintViolation.getMessage());
        });
        CustomResponseDto customResponseDTO = new CustomResponseDto(100422, "");
        customResponseDTO.setData(validatedResult.getErrors());
        return customResponseDTO;

    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
     *
     * @param e MethodArgumentNotValidException
     * @return CustomResponseDto
     */
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

    /**
     * 处理请求参数格式错误 @RequestBody JSON 解析错误时抛出
     *
     * @param e HttpMessageNotReadableException
     * @return CustomResponseDto
     */
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
