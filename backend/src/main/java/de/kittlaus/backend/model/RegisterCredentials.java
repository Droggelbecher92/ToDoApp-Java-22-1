package de.kittlaus.backend.model;

import lombok.Data;

@Data
public class RegisterCredentials {

    private String username;
    private String password;
    private String passwordAgain;

}
