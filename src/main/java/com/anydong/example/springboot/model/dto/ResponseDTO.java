package com.anydong.example.springboot.model.dto;

import lombok.Data;

/**
 * @author Where
 */
@Data
public class ResponseDTO {
    private Integer code;
    private String message;
    private Object data;

    public ResponseDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDTO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
