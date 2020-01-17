package com.anydong.example.springboot.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author where
 */
public class HttpBearerAuthenticationFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private boolean ignoreFailure = false;

    public HttpBearerAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.ignoreFailure = true;
    }

    public HttpBearerAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 收集 AuthenticationDetails
        HttpBearerAuthenticationDetailsSource detailsSource = new HttpBearerAuthenticationDetailsSource();
        HttpBearerAuthenticationDetails details = detailsSource.buildDetails(httpServletRequest);
        if (null == details.getBearerToken()) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        try {
            // 验证
            Authentication authentication = this.authenticationManager.authenticate(new HttpBearerAuthentication(details));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            if (this.ignoreFailure) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                this.authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e);
            }
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
