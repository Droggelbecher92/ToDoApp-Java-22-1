package de.kittlaus.backend.controller;


import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    ToDo: - Endpunkt um alle ToDoItems zu erhalten => Rückgabe List<ToDoItem>
    ToDo: - Endpunkt um ein einzelnes ToDoItem zu erhalten => Rückgabe ToDoItem
    ToDo: - Endpunkt um ein ToDoItem hinzuzufügen => Rückgabe ToDoItem (das hinzugefügte)
    ToDo: - Endpunkt um ein ToDoItem zu ändern => Rückgabe ToDoItem (das geänderte)
    ToDo: - Endpunkt um ein ToDoItem zu löschen => Rückgabe ToDoItem (das gelöschte)
     */


@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


}
