package de.kittlaus.backend.todo;


import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.todo.ToDoRepo;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class ToDoService {private final ToDoRepo toDoRepo;


    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    public List<ToDoItem> returnAllToDos(Principal principal) {
        return toDoRepo.findAllByUser(principal.getName());
    }

    public ToDoItem saveNewToDo(ToDoItem itemtoAdd, Principal principal) {
        itemtoAdd.setUser(principal.getName());
        return toDoRepo.save(itemtoAdd);
    }

    public ToDoItem findToDoById(String id, Principal principal) {
        return toDoRepo.findByIdAndUser(id, principal.getName()).orElseThrow(() -> new IllegalArgumentException("No ToDo found with ID: "+id));
    }

    public ToDoItem advanceToDo(ToDoItem itemToChange) {
        itemToChange.setStatus(itemToChange.getStatus().advance());
        return toDoRepo.save(itemToChange);
    }

    public ToDoItem deleteToDo(String idToDelete, Principal principal) {
        Optional<ToDoItem> optToDo = toDoRepo.findByIdAndUser(idToDelete, principal.getName());
        ToDoItem toDoItem = optToDo.orElseThrow(() -> new IllegalArgumentException("No ToDo found with ID: " + idToDelete));
        toDoRepo.delete(toDoItem);
        return toDoItem;
    }

    public ToDoItem updateToDo(ToDoItem changedItem) {
        if (toDoRepo.findById(changedItem.getId()).isPresent()){
            toDoRepo.save(changedItem);
        }
        return changedItem;
    }
}
