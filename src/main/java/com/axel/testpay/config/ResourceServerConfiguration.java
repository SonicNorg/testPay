package com.axel.testpay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/payments/**")
                .and().authorizeRequests().antMatchers("/payments/**").authenticated()
                .and().csrf().disable()
                .anonymous().disable()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
