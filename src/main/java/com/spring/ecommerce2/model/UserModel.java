package com.spring.ecommerce2.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 6, message = "Password should be atleast 6 characters")
    private String password;

    private String address;
    private String hp;
    private String roles = "user";
    private String images;
    private Boolean isActive = true;
}