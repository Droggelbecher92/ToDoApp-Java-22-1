package de.kittlaus.backend.repo;


import de.kittlaus.backend.model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Repository
public class ToDoRepo {

    private final HashMap<String, ToDoItem> allTodos = new HashMap<>();

    public List<ToDoItem> returnAll() {
        return allTodos.values().stream().toList();
    }

    public ToDoItem addToDo(ToDoItem itemtoAdd) {
        allTodos.put(itemtoAdd.getId(),itemtoAdd);
        return itemtoAdd;
    }

    public Optional<ToDoItem> findByID(String id) {
        return Optional.ofNullable(allTodos.get(id));
    }

    public ToDoItem updateToDo(ToDoItem advancedItem) {
        allTodos.put(advancedItem.getId(),advancedItem);
        return advancedItem;
    }

    public ToDoItem removeToDo(String idToDelete) {
        return allTodos.remove(idToDelete);
    }

}
