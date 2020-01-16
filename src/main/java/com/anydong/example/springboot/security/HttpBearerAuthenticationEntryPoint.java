package com.anydong.example.springboot.security;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.model.dto.CustomResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class HttpBearerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpBearerAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        CustomResponseDto responseDto = null;
        if (e instanceof BadCredentialsException) {
            responseDto = new CustomResponseDto(100403, "Token 不合法");
        }
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        OutputStream os = httpServletResponse.getOutputStream();
        os.write(JSON.toJSONBytes(responseDto));
        os.close();
        os.flush();
    }
}
