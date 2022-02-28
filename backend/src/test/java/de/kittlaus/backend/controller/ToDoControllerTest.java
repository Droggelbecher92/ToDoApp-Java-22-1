package de.kittlaus.backend.controller;

import de.kittlaus.backend.model.Status;
import de.kittlaus.backend.model.ToDoItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToDoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    //Test-Objekte

    final private ToDoItem toDoItem1 = new ToDoItem("Wäsche waschen");
    final private ToDoItem toDoItem2 = new ToDoItem("Tests schreiben");



    @Test
    @Order(1)
    void should1AddNewTodo(){
        //GIVEN

        //WHEN
        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.postForEntity("/api/todo", toDoItem1, ToDoItem.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ToDoItem actual = actualResponse.getBody();
        assertEquals(toDoItem1.getTask(),actual.getTask());
    }

    @Test
    @Order(2)
    void shouldDeleteToDoByID(){
        //GIVEN
        String url = "/api/todo/"+toDoItem1.getId();
        //WHEN
        testRestTemplate.delete(url);
        ResponseEntity<ToDoItem[]> actualResponse = testRestTemplate.getForEntity("/api/todo", ToDoItem[].class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        List<ToDoItem> actual = Arrays.stream(Objects.requireNonNull(actualResponse.getBody())).toList();
        System.out.println("LÄNGE ==="+actual.size());
        assertFalse(actual.contains(toDoItem1));
    }



    @Test
    @Order(3)
    void shouldReturnAllToDos(){
        //GIVEN
        testRestTemplate.postForEntity("/api/todo", toDoItem2, ToDoItem.class);
        testRestTemplate.postForEntity("/api/todo", toDoItem1, ToDoItem.class);
        //WHEN
        ResponseEntity<ToDoItem[]> actualResponse = testRestTemplate.getForEntity("/api/todo", ToDoItem[].class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        List<ToDoItem> actual = Arrays.stream(Objects.requireNonNull(actualResponse.getBody())).toList();
    }



    @Test
    @Order(4)
    void shouldReturnToDoByID(){
        //GIVEN
        ResponseEntity<ToDoItem[]> allTodosResponse = testRestTemplate.getForEntity("/api/todo", ToDoItem[].class);
        String id = allTodosResponse.getBody()[0].getId();
        ToDoItem expected = allTodosResponse.getBody()[0];
        String url = "/api/todo/"+id;
        //WHEN

        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.getForEntity(url,ToDoItem.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ToDoItem actual = actualResponse.getBody();
        assertEquals(expected,actual);
    }

}