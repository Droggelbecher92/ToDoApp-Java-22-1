package de.kittlaus.backend.service;


import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.repo.ToDoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
        ToDo: - Methode um alle ToDoItems vom Repo zu erhalten
        ToDo: - Methode um ein ToDoItem nach ID vom Repo zu erhalten
        ToDo: - Methode um ein neues ToDoItem im repo zu speichern
        ToDo: - Methode um den Status eines ToDoItems auf den nächsten Status zu setzen und dann abzuspeichern
        ToDo: - Methode um ein ToDoItem aus dem Repo zu löschen
        ToDo: - Achte auf Möglichkeiten für Optionals und exceptions!
         */

@Service
public class ToDoService {

    private final ToDoRepo toDoRepo;


    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }


}
