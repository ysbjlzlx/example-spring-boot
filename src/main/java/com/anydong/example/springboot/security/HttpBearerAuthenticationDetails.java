package com.anydong.example.springboot.security;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author where
 */
@Data
public class HttpBearerAuthenticationDetails {
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private final String remoteAddress;
    private final String bearerToken;

    public HttpBearerAuthenticationDetails(HttpServletRequest request) {
        this.remoteAddress = request.getRemoteAddr();

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith(BEARER_TOKEN_PREFIX)) {
            this.bearerToken = header.substring(BEARER_TOKEN_PREFIX.length());
        } else {
            this.bearerToken = null;
        }
    }
}
