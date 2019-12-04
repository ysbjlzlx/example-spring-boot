package com.anydong.example.springboot.dto;

import lombok.Data;

@Data
public class CustomResponseDTO {
    private int code;
    private String message;
    private Object data;

    public CustomResponseDTO() {
    }

    public CustomResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
