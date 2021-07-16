package com.anydong.example.springboot.security;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author where
 */
@Component
@Slf4j
public class HttpBearerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
        ResponseDTO responseDto = null;
        log.info(e.getMessage());
        if (e instanceof BadCredentialsException) {
            responseDto = new ResponseDTO(100403, "Token 不合法");
        } else if (e instanceof AuthenticationCredentialsNotFoundException) {
            responseDto = new ResponseDTO(100403, "未找到 Token");
        } else {
            responseDto = new ResponseDTO(100401, e.getMessage());
            e.printStackTrace();
        }

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream os = httpServletResponse.getOutputStream();
        os.write(JSON.toJSONBytes(responseDto));
        os.flush();
        os.close();
    }
}
