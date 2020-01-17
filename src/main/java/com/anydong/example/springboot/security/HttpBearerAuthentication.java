package com.anydong.example.springboot.security;

import com.anydong.example.springboot.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author where
 */
public class HttpBearerAuthentication extends AbstractAuthenticationToken {
    private String credentials;
    private User principal;

    public HttpBearerAuthentication(HttpBearerAuthenticationDetails details) {
        super(null);
        this.setAuthenticated(false);
        this.setDetails(details);
    }

    public HttpBearerAuthentication(HttpBearerAuthenticationDetails details, User principal,
                                    Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.setAuthenticated(true);
        this.setDetails(details);
        this.credentials = details.getBearerToken();
        this.principal = principal;
    }

    @Override
    public String getCredentials() {
        return this.credentials;
    }

    @Override
    public User getPrincipal() {
        return this.principal;
    }
}
