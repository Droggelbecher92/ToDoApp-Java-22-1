package de.kittlaus.backend.service;


import de.kittlaus.backend.repo.ToDoRepo;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    private final ToDoRepo toDoRepo;


    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }
}
