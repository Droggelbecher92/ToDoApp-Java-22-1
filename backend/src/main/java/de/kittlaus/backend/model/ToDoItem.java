package de.kittlaus.backend.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ToDoItem {

    private final String id;
    private String text;
    private Status status;
    private Date start;
    private Date end;

    public ToDoItem(String text) {
        Instant now = Instant.now();
        start = Date.from(now);
        end = Date.from(now.plus(Duration.ofDays(7)));
        id = UUID.randomUUID().toString();
        this.text = text;
        status = Status.OPEN;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return Objects.equals(id, toDoItem.id) && Objects.equals(text, toDoItem.text) && status == toDoItem.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, status);
    }
}
