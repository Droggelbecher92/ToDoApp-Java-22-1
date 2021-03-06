package de.kittlaus.backend.service;

import de.kittlaus.backend.model.Status;
import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.todo.ToDoRepo;
import de.kittlaus.backend.todo.ToDoService;
import org.junit.jupiter.api.Test;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    Principal testPrincipal = new Principal() {
        @Override
        public String getName() {
            return "Test";
        }
    };

    @Test
    void shouldAddNewTodo(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.save(testItem)).thenReturn(testItem);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.saveNewToDo(testItem, testPrincipal);
        //THEN
        assertEquals(testItem,actual);
    }

    @Test
    void shouldReturnListOfToDos(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoItem testItem2 = new ToDoItem("An die frische Luft gehen!");
        List<ToDoItem> toDoItemList = List.of(testItem,testItem2);
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.findAllByUser("Test")).thenReturn(toDoItemList);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        List<ToDoItem> actual = testService.returnAllToDos(testPrincipal);
        //THEN
        assertEquals(toDoItemList,actual);
    }

    @Test
    void shouldFindToDoById(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        String id = testItem.getId();
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.findByIdAndUser(id, "Test")).thenReturn(Optional.of(testItem));
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.findToDoById(id,testPrincipal);
        //THEN
        assertEquals(testItem,actual);
    }

    @Test
    void shouldThrowWithUnknownID(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.findByIdAndUser("unknown","Test")).thenReturn(Optional.empty());
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        try {
            testService.findToDoById("unknown",testPrincipal);
            fail();
        } catch (IllegalArgumentException e){
            //THEN
            assertEquals("No ToDo found with ID: unknown",e.getMessage());
        }
    }

    @Test
    void shouldAdvanceToDoByID(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        testItem.setStatus(Status.OPEN);
        ToDoItem advancedItem = testItem;
        advancedItem.setStatus(advancedItem.getStatus().advance());
        String id = testItem.getId();
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.save(advancedItem)).thenReturn(advancedItem);
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.advanceToDo(testItem);
        //THEN
        assertEquals(advancedItem,actual);
    }

    @Test
    void shouldDeleteToDoByID(){
        //GIVEN
        ToDoItem testItem = new ToDoItem("Tests schreiben");
        String id = testItem.getId();
        ToDoRepo toDoRepoMock = mock(ToDoRepo.class);
        when(toDoRepoMock.findByIdAndUser(id, "Test")).thenReturn(Optional.of(testItem));
        ToDoService testService = new ToDoService(toDoRepoMock);
        //WHEN
        ToDoItem actual = testService.deleteToDo(testItem.getId(),testPrincipal);
        //THEN
        verify(toDoRepoMock).delete(testItem);
        assertEquals(testItem,actual);
    }

}