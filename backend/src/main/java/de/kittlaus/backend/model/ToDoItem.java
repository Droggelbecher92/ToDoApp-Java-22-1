package de.kittlaus.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ToDoItem {

    private String id;
    private String task;
    private String description;
    private Status status;

    public ToDoItem(String task) {
        this.task = task;
    }

    public ToDoItem() {
        id = UUID.randomUUID().toString();
        status = Status.OPEN;
    }
}
