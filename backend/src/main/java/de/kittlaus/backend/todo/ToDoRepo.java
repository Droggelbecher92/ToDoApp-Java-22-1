package de.kittlaus.backend.todo;


import de.kittlaus.backend.model.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Repository
public interface ToDoRepo extends MongoRepository<ToDoItem,String> {

    public List<ToDoItem> findAllByUser(String user);
    public Optional<ToDoItem> findByIdAndUser(String id, String user);

}
