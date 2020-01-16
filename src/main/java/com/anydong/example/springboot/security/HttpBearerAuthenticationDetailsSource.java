package com.anydong.example.springboot.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author where
 */
public class HttpBearerAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest,
        HttpBearerAuthenticationDetails> {
    @Override
    public HttpBearerAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new HttpBearerAuthenticationDetails(request);
    }
}
