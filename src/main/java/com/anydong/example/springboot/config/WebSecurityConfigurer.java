package com.anydong.example.springboot.config;

import com.anydong.example.springboot.security.HttpBearerAccessDeniedHandler;
import com.anydong.example.springboot.security.HttpBearerAuthenticationEntryPoint;
import com.anydong.example.springboot.security.HttpBearerAuthenticationFilter;
import com.anydong.example.springboot.security.HttpBearerAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author anydong
 */
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private HttpBearerAuthenticationProvider httpBearerAuthenticationProvider;
    @Autowired
    private HttpBearerAuthenticationEntryPoint httpBearerAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/locale").permitAll()
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAt(
                        new HttpBearerAuthenticationFilter(authenticationManager(), httpBearerAuthenticationEntryPoint),
                        BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(httpBearerAuthenticationEntryPoint)
                .accessDeniedHandler(httpBearerAccessDeniedHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(httpBearerAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpBearerAccessDeniedHandler httpBearerAccessDeniedHandler() {
        return new HttpBearerAccessDeniedHandler();
    }
}
