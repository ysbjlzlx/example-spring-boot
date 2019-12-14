package com.anydong.example.springboot.dto;

import lombok.Data;

/**
 * @author Where
 */
@Data
public class CustomResponseDTO {
    private Integer code;
    private String message;
    private Object data;

    public CustomResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomResponseDTO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
