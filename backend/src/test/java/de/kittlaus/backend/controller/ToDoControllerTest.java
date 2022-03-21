package de.kittlaus.backend.controller;

import de.kittlaus.backend.model.RegisterCredentials;
import de.kittlaus.backend.model.ToDoItem;
import de.kittlaus.backend.model.Token;
import de.kittlaus.backend.user.MyUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToDoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    //Test-Objekte

    final private ToDoItem toDoItem1 = new ToDoItem("Wäsche waschen");
    final private ToDoItem toDoItem2 = new ToDoItem("Tests schreiben");
    final private RegisterCredentials user = new RegisterCredentials("Test", "test", "test");

    @Test
    @Order(1)
    void should1AddNewTodo(){
        //GIVEN
        testRestTemplate.postForEntity("/api/user",user, MyUser.class);
        ResponseEntity<Token> tokenResponseEntity = testRestTemplate.postForEntity("/auth", user, Token.class);
        String token = tokenResponseEntity.getBody().getToken();
        //WHEN

        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.exchange("/api/todo",
                HttpMethod.POST,
                new HttpEntity<>(toDoItem1, createHeaders(token)),
                ToDoItem.class);
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
        ResponseEntity<Token> tokenResponseEntity = testRestTemplate.postForEntity("/auth", user, Token.class);
        String token = tokenResponseEntity.getBody().getToken();
        //WHEN
        testRestTemplate.delete(url);
        ResponseEntity<ToDoItem[]> actualResponse = testRestTemplate.exchange("/api/todo",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                ToDoItem[].class);
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
        ResponseEntity<Token> tokenResponseEntity = testRestTemplate.postForEntity("/auth", user, Token.class);
        String token = tokenResponseEntity.getBody().getToken();
        testRestTemplate.exchange("/api/todo",HttpMethod.POST,new HttpEntity<>(toDoItem2,createHeaders(token)), ToDoItem.class);
        testRestTemplate.exchange("/api/todo", HttpMethod.POST,new HttpEntity<>(toDoItem1,createHeaders(token)), ToDoItem.class);
        //WHEN
        ResponseEntity<ToDoItem[]> actualResponse = testRestTemplate.exchange("/api/todo",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                ToDoItem[].class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        List<ToDoItem> actual = Arrays.stream(Objects.requireNonNull(actualResponse.getBody())).toList();
    }



    @Test
    @Order(4)
    void shouldReturnToDoByID(){
        //GIVEN
        ResponseEntity<Token> tokenResponseEntity = testRestTemplate.postForEntity("/auth", user, Token.class);
        String token = tokenResponseEntity.getBody().getToken();
        ResponseEntity<ToDoItem[]> allTodosResponse = testRestTemplate.exchange("/api/todo",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                ToDoItem[].class);
        String id = allTodosResponse.getBody()[0].getId();
        ToDoItem expected = allTodosResponse.getBody()[0];
        String url = "/api/todo/"+id;
        //WHEN

        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                ToDoItem.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ToDoItem actual = actualResponse.getBody();
        assertEquals(expected,actual);
    }

    private HttpHeaders createHeaders(String token){
        String authHeader = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }

}
