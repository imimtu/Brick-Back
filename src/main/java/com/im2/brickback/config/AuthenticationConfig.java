package com.im2.brickback.config;

//import com.im2.brickback.config.filter.JwtTokenFilter;
import com.im2.brickback.config.filter.JwtTokenFilter;
import com.im2.brickback.exception.CustomAuthenticationEntryPoint;
import com.im2.brickback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// for spring-security
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig { // extends WebsecurityConfigurerAdapter -> deprecated ( https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter )

    private final UserService userService;

    @Value("${jwt.secret-key}")
    private String key;

    // DEBUG
    // JwtTokenFilter : Error occurs while getting header. header is null or invalid
    // join, login 의 경우는 filter chain 에서 허용하도록 한다.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/api/*")
                .requestMatchers("/api/*/users/join", "/api/*/users/login"); // filter chain 타지 않게 한다.
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // .antMatcher deprecated ( https://docs.spring.io/spring-security/reference/5.8/migration/servlet/config.html )
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .requestMatchers(
                                                // swagger 허용
                                                "/swagger-ui/**",
                                                "/v3/api-docs/**",
                                                "/api/profile/**"
                                                // user
                                                // "/api/*/users/**"
//                                                // brick  // 이건 authenticated 쪽에 들어갔어야했는데... 삽질했네;;;
//                                                "/api/*/bricks",
//                                                "/api/*/bricks/**"
                                        ).permitAll()
                                        .requestMatchers("/api/**").authenticated().anyRequest().permitAll()
                                        .and()
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .exceptionHandling()
                                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                        .and()
                                        .addFilterBefore(new JwtTokenFilter(key, userService ), UsernamePasswordAuthenticationFilter.class)
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
