package com.tfg.proyect.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ApiError {

  @NonNull
  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  private Date date = Date.valueOf(String.valueOf(LocalDateTime.now()));

  @NonNull
  private String message;

}
