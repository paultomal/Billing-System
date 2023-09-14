package com.paul.billing_system.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    @NotEmpty(message = "Username shouldn't be NULL!!")
    private String username;
    @NotEmpty(message = "Password shouldn't be NULL!!")
    private String password;
}
