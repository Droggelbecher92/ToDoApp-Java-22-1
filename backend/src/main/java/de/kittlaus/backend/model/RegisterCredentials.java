package de.kittlaus.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterCredentials {

    private String username;
    private String password;
    private String passwordAgain;

}
