package com.anydong.example.springboot.security;

import com.anydong.example.springboot.domain.UserDO;
import com.anydong.example.springboot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class HttpBearerAuthenticationProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;
    private final String DEFAULT_TOKEN = "Token";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpBearerAuthenticationDetails details = (HttpBearerAuthenticationDetails) authentication.getDetails();
        if (null == details.getBearerToken()) {
            throw new AuthenticationCredentialsNotFoundException("Token 未找到");
        }
        if (!DEFAULT_TOKEN.equals(details.getBearerToken())) {
            throw new BadCredentialsException("Token 不合法");
        }
        // 获取 Token 对应用户
        UserDO userDO = this.userRepository.findFirstByIdIsNotNull();
        return new HttpBearerAuthentication(details, userDO, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HttpBearerAuthentication.class.isAssignableFrom(authentication);
    }
}
