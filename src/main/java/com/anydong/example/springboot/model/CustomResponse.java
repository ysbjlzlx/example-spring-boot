package com.anydong.example.springboot.model;

import lombok.Data;

/**
 * CustomResponse
 *
 * @author Where
 * @date 2019/11/11
 */
@Data
public class CustomResponse {
    private int code;
    private String message;
    private Object data;

    public CustomResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
