package de.kittlaus.backend.controller;


import de.kittlaus.backend.service.ToDoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;


    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
}
