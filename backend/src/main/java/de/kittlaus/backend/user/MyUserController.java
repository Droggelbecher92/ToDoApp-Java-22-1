package de.kittlaus.backend.user;

import de.kittlaus.backend.model.RegisterCredentials;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class MyUserController {

    private final MyUserService myUserService;
    private final PasswordEncoder passwordEncoder;

    public MyUserController(MyUserService myUserService, PasswordEncoder passwordEncoder) {
        this.myUserService = myUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public MyUser postNewUser (@RequestBody RegisterCredentials credentials){
        if (!credentials.getPassword().equals(credentials.getPasswordAgain())){
            throw new IllegalArgumentException("Passwörter nicht identisch");
        }
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        return myUserService.saveNewUser(credentials);
    }

}
