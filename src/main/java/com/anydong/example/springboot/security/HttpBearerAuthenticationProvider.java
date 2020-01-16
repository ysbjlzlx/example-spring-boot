package com.anydong.example.springboot.security;

import com.anydong.example.springboot.domain.UserDo;
import com.anydong.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class HttpBearerAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    private final String DEFAULT_TOKEN = "Token";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpBearerAuthenticationDetails details = (HttpBearerAuthenticationDetails) authentication.getDetails();
        // 判断 Token 是否存在
        if (null == details.getBearerToken()) {
            throw new AuthenticationCredentialsNotFoundException("未找到 Token");
        }
        // 校验 Token 是否合法
        if (!DEFAULT_TOKEN.equals(details.getBearerToken())) {
            throw new BadCredentialsException("Token 不合法");
        }
        // 获取 Token 对应用户
        UserDo userDo = this.userRepository.findFirstByIdIsNotNull();

        return new HttpBearerAuthentication(details, userDo, null);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(HttpBearerAuthentication.class);
    }
}
