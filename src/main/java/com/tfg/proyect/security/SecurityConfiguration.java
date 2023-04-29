package com.tfg.proyect.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests(request -> request
            .requestMatchers("/admin/**")
            .hasRole("admin"))
        .authorizeHttpRequests(form -> form
            .requestMatchers("/login")
            .permitAll())
        .authorizeHttpRequests(error -> error
            .requestMatchers("/error")
            .permitAll())
        .anonymous()
        .disable()
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .failureUrl("/login?error=true")
        .and()
        .logout()
        .logoutUrl("/logout")
        .deleteCookies("JSESSIONID");
    return http.build();
  }


}
