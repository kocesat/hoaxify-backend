package com.hoaxify.hoaxifybackend.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 140, message = "Length of username must be between {min} and {max}")
    @UniqueUsername
    private String username;

    @NotNull(message = "Display name cannot be null")
    @Size(min = 4, max = 140, message = "Length of display name must be between {min} and {max}")
    private String displayName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 140, message = "Length of password name must be between {min} and {max}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;
}
