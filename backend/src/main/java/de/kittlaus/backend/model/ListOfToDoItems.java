package de.kittlaus.backend.model;

import java.util.List;
import java.util.Objects;


//Dieses Objekt existiert nur für Integrationstests
public class ListOfToDoItems {

    public ListOfToDoItems(List<ToDoItem> list) {
        this.list = list;
    }

    public ListOfToDoItems() {
    }

    private List<ToDoItem> list;

    public List<ToDoItem> getList() {
        return list;
    }

    public void setList(List<ToDoItem> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfToDoItems that = (ListOfToDoItems) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
