package com.plekhanov.webService.entities;


import com.plekhanov.webService.web.security.Role;
import lombok.Data;

@Data
public class UserRole {

    private Long id;
    private Role role;
}
