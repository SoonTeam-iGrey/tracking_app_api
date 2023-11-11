package com.hackathoon.datavisualizer.security.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "The first name is required to create an account")
    private String firstName;
    @NotBlank(message = "The last name is required to create an account")
    private String lastName;
    @NotBlank(message = "The email is required to create an account")
    private String email;
    @Size(min = 5, max = 16, message = "The username should have between 5 and 16 characters!")
    private String username;
    @NotBlank(message = "The phone number is required to create an account")
    @Size(min = 10, max = 10, message = "The phone number should have 10 characters")
    private String phoneNumber;
    @Size(min = 8, max = 64, message = "The password should have between 8 and 64 characters")
    private String password;

}
