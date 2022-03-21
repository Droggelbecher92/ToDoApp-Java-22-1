package de.kittlaus.backend.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MyUserRepo extends MongoRepository<MyUser,String> {

    public Optional<MyUser> findByUsername(String username);

}
