package com.wilinskiw.universal.user_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public UserSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                            authorize.requestMatchers("/", "/login/**", "/logout", "/register/**").permitAll();
                            authorize.requestMatchers("/admin/**").hasRole("ADMIN");
                            authorize.anyRequest().authenticated();
                        }
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .loginProcessingUrl("/authenticateTheUser")
                                .successForwardUrl("/login/success")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/api/auth/logout")
                                .logoutSuccessUrl("/")
                                .permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
