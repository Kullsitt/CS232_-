package com.coursestu.central_portal.config;

import com.coursestu.central_portal.service.TUAuthService;
import com.coursestu.central_portal.dto.TULoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // เปิดทุก route ไว้ก่อน
            )
            .formLogin(form -> form.disable())  // ปิด Spring Security login
            .logout(logout -> logout.disable());

        return http.build();
    }
}