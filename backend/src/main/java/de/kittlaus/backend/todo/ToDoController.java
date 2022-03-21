package de.kittlaus.backend.todo;


import de.kittlaus.backend.model.ToDoItem;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/todo")
public class ToDoController {private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoItem> getAllTodos(Principal principal){
        return toDoService.returnAllToDos(principal);
    }

    @PostMapping
    public ToDoItem postNewToDo(@RequestBody ToDoItem itemtoAdd, Principal principal){
        return toDoService.saveNewToDo(itemtoAdd, principal);
    }

    @GetMapping("/{id}")
    public ToDoItem getToDoById(@PathVariable String id, Principal principal){
        return toDoService.findToDoById(id, principal);
    }

    @PutMapping()
    public ToDoItem putUpdateForToDoStatus(@RequestBody ToDoItem itemToChange){
        return toDoService.advanceToDo(itemToChange);
    }

    @DeleteMapping("/{id}")
    public ToDoItem deleteToDoById(@PathVariable String id, Principal principal){
        return toDoService.deleteToDo(id, principal);
    }

    @PutMapping("/{id}")
    public ToDoItem changeItem (@RequestBody ToDoItem changedItem){
        return toDoService.updateToDo(changedItem);
    }
}
