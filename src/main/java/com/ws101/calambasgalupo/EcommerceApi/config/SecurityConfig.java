package com.ws101.calambasgalupo.EcommerceApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //  Disable CSRF
                .csrf(csrf -> csrf.disable())

                //  AUTH RULES
                .authorizeHttpRequests(auth -> auth

                        //  PUBLIC ENDPOINTS (FIXED)
                        .requestMatchers(
                                "/login",
                                "/register",
                                "/api/v1/auth/register",
                                "/api/v1/auth/login",
                                "/css/**",
                                "/js/**"
                        ).permitAll()

                        // USER ACCESS
                        .requestMatchers("/api/products/**").authenticated()

                        // ADMIN ONLY
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // EVERYTHING ELSE
                        .anyRequest().authenticated()
                )

                //  FORM LOGIN (for browser login page)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index.html", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                // LOGOUT
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                //  401 / 403 HANDLING
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, authEx) -> {
                            res.setStatus(401);
                            res.getWriter().write("Unauthorized - Please login");
                        })
                        .accessDeniedHandler((req, res, accessDeniedEx) -> {
                            res.setStatus(403);
                            res.getWriter().write("Forbidden - Access denied");
                        })
                );

        return http.build();
    }

    // REQUIRED FOR PASSWORD ENCODING
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}