package de.kittlaus.backend.service;


import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.repo.ToDoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ToDoService {private final ToDoRepo toDoRepo;


    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    public List<ToDoItem> returnAllToDos() {
        return toDoRepo.returnAll();
    }

    public ToDoItem saveNewToDo(ToDoItem itemtoAdd) {
        itemtoAdd.setId(UUID.randomUUID().toString());
        return toDoRepo.addToDo(itemtoAdd);
    }

    public ToDoItem findToDoById(String id) {
        return toDoRepo.findByID(id).orElseThrow(() -> new IllegalArgumentException("No ToDo found with ID: "+id));
    }

    public ToDoItem advanceToDo(ToDoItem itemToChange) {
        ToDoItem advancedItem = itemToChange;
        advancedItem.setStatus(advancedItem.getStatus().advance());
        return toDoRepo.updateToDo(advancedItem);
    }

    public ToDoItem deleteToDo(String idToDelete) {
        return toDoRepo.removeToDo(idToDelete);
    }

    public ToDoItem updateToDo(ToDoItem changedItem) {
        if (toDoRepo.findByID(changedItem.getId()).isPresent()){
            toDoRepo.addToDo(changedItem);
        }
        return changedItem;
    }
}
