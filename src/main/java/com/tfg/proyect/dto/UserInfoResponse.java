package com.tfg.proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type User info response.
 */
@AllArgsConstructor
@Data
public class UserInfoResponse {

    private String firstName;
    private String lastName;
    private String username;
    private String rol;

}
