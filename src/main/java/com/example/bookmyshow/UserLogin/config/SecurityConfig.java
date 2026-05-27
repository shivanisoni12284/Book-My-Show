package com.example.bookmyshow.UserLogin.config;

import com.example.bookmyshow.UserLogin.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/bookmyshow/auth/**")//these APIs are public.
                        .permitAll()   //no login required.

                        .requestMatchers("/api/bookmyshow/admin/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/bookmyshow/user/**")
                        .hasRole("USER")
                        .anyRequest().authenticated()  //every other API needs authentication.
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);


        return http.build();  //finally builds the security system.



    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16,32,1,60000,2);
        return encoder;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }

}
