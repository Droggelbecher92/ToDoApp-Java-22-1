package de.kittlaus.backend.repo;


import de.kittlaus.backend.model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ToDoRepo {

    private final HashMap<String, ToDoItem> allTodos = new HashMap<>();

}
