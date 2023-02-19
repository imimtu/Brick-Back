package com.im2.brickback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// for spring-security
@Configuration
@EnableWebSecurity
public class AuthenticationConfig { // extends WebsecurityConfigurerAdapter -> deprecated ( https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter )
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // .antMatcher deprecated ( https://docs.spring.io/spring-security/reference/5.8/migration/servlet/config.html )
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .requestMatchers(
                                                "/swagger*", // swagger 허용
                                                "/api/*/users/join",
                                                "/api/*/users/login"
                                        )
                                        .permitAll()
                                        .requestMatchers("/api/**").authenticated()
                                        .and()
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                // TODO : 추가작업
                                //.and()
                                // .exceptionHandling()
                                //.authenticationEntryPoint()
                                ;
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                )
        ;
        return http.build();
    }

}
