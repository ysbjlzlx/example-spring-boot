package com.anydong.example.springboot.security;

import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Getter
    private AuthenticationManager authenticationManager;

    public HttpBearerAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 收集 AuthenticationDetails
        HttpBearerAuthenticationDetailsSource detailsSource = new HttpBearerAuthenticationDetailsSource();
        HttpBearerAuthenticationDetails details = detailsSource.buildDetails(httpServletRequest);
        // 验证
        Authentication authentication = this.getAuthenticationManager().authenticate(new HttpBearerAuthentication(details));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
