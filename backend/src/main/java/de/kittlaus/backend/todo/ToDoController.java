package de.kittlaus.backend.todo;


import de.kittlaus.backend.model.ToDoItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/todo")
public class ToDoController {private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoItem> getAllTodos(){
        return toDoService.returnAllToDos();
    }

    @PostMapping
    public ToDoItem postNewToDo(@RequestBody ToDoItem itemtoAdd){
        return toDoService.saveNewToDo(itemtoAdd);
    }

    @GetMapping("/{id}")
    public ToDoItem getToDoById(@PathVariable String id){
        return toDoService.findToDoById(id);
    }

    @PutMapping()
    public ToDoItem putUpdateForToDoStatus(@RequestBody ToDoItem itemToChange){
        return toDoService.advanceToDo(itemToChange);
    }

    @DeleteMapping("/{id}")
    public ToDoItem deleteToDoById(@PathVariable String id){
        return toDoService.deleteToDo(id);
    }

    @PutMapping("/{id}")
    public ToDoItem changeItem (@RequestBody ToDoItem changedItem){
        return toDoService.updateToDo(changedItem);
    }
}
