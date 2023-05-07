package com.tfg.proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserInfoResponse {

  private String firstName;
  private String lastName;
  private String username;
  private String rol;

}
