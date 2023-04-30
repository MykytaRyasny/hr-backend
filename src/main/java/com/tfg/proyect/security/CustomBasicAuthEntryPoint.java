package com.tfg.proyect.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthEntryPoint extends BasicAuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    super.commence(request, response, authException);

    response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
    response.setContentType("application/json");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, authException.getMessage());
    String stringApiError = objectMapper.writeValueAsString(apiError);
    PrintWriter writer = response.getWriter();
    writer.println(stringApiError);
  }


  @Override
  public void afterPropertiesSet() {
    setRealmName("library.com");
    super.afterPropertiesSet();
  }
}
