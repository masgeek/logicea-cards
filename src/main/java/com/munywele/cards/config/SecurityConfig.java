package com.munywele.cards.config;

import com.munywele.cards.service.UserService;
import com.munywele.cards.utils.AuthEntryPoint;
import com.munywele.cards.utils.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final AuthEntryPoint unauthorizedHandler;


    private final UserService userService;

    /*
    "/api/v1/users/auth", "/actuator",
                                "/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs"
     */
    private static final String[] AUTH_WHITE_LIST = {
            "/api/v1/users/auth",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/swagger-ui/index.html",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/api-docs/**",
            "/swagger-resources/**",
            "/error"
    };

    public SecurityConfig(AuthEntryPoint unauthorizedHandler, UserService userService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authJwtFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(requests ->
                requests.requestMatchers(AUTH_WHITE_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated());

        httpSecurity.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.formLogin(formLogin -> formLogin.disable());

        httpSecurity.exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler));
        httpSecurity.addFilterBefore(authJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authenticationProvider(authenticationProvider());

        return httpSecurity.build();
    }


}
