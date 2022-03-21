package de.kittlaus.backend.user;

import de.kittlaus.backend.model.RegisterCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepo myUserRepo;


    public Optional<MyUser> findByUsername(String username) {
        return myUserRepo.findByUsername(username);
    }

    public MyUser saveNewUser(RegisterCredentials credentials) {
        if (findByUsername(credentials.getUsername()).isPresent()){
            throw new IllegalArgumentException("Nutzername schjon vergeben");
        }
        MyUser newUser = new MyUser();
        newUser.setUsername(credentials.getUsername());
        newUser.setRole("USER");
        newUser.setPassword(credentials.getPassword());
        return myUserRepo.save(newUser);
    }
}
