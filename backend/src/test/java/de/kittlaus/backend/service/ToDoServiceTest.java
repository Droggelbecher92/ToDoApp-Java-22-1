package de.kittlaus.backend.service;

import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.repo.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    @Test
    void shouldAddNewTodo(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.addToDo(toDoRepoMock)).thenReturn(testItem);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.saveNewToDo(testItem);
        //THEN
        verify(toDoRepoMock.addToDo(toDoRepoMock));
        assertEquals(testItem,actual);
    }

    @Test
    void shouldReturnListOfToDos(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoItem testItem2 = new ToDoItem("An die frische Luft gehen!");
        List<ToDoItem> toDoItemList = List.of(testItem,testItem2);
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.returnAll()).thenReturn(toDoItemList);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        List<ToDoItem> actual = testService.returnAllToDos();
        //THEN
        verify(toDoRepoMock.returnAll());
        assertEquals(toDoItemList,actual);
    }

    @Test
    void shouldFindToDoById(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        String id = testItem.getId();
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.findByID(id)).thenReturn(Optional.of(testItem));
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.findToDoById(id);
        //THEN
        verify(toDoRepoMock.findByID(id));
        assertEquals(testItem,actual);
    }

    @Test
    void shouldThrowWithUnknownID(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.findByID("unknown")).thenReturn(Optional.empty());
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN

        //THEN
        assertThrows(testService.findToDoById("unknown"),IllegalArgumentException.class);
    }

    @Test
    void shouldAdvanceToDoByID(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoItem advancedItem = testItem;
        advancedItem.setStatus(advancedItem.getStatus().advance());
        String id = testItem.getId();
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.updateToDo(advancedItem)).thenReturn(advancedItem);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.advanceToDo(testItem);
        //THEN
        verify(toDoRepoMock.updateToDo(advancedItem));
        assertEquals(advancedItem,actual);
    }

    @Test
    void shouldDeleteToDoByID(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        String id = testItem.getId();
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.removeToDo(id)).thenReturn(testItem);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.deleteToDo(testItem);
        //THEN
        verify(toDoRepoMock.removeToDo(id));
        assertEquals(testItem,actual);
    }

}