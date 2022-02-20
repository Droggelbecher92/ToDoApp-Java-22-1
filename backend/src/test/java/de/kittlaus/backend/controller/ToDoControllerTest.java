package de.kittlaus.backend.controller;

import de.kittlaus.backend.model.ListOfToDoItems;
import de.kittlaus.backend.model.Status;
import de.kittlaus.backend.model.ToDoItem;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    void shouldAddNewTodo(){
        //GIVEN

        //WHEN
        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.postForEntity("/todo", toDoItem1, ToDoItem.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ToDoItem actual = actualResponse.getBody();
        assertEquals(toDoItem1,actual);
    }

    @Test
    @Order(2)
    void shouldReturnAllToDos(){
        //GIVEN
        testRestTemplate.postForEntity("/todo", toDoItem2, ToDoItem.class);
        //WHEN
        ResponseEntity<ListOfToDoItems> actualResponse = testRestTemplate.getForEntity("/todo", ListOfToDoItems.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ListOfToDoItems actual = actualResponse.getBody();
        assert actual != null;
        assertTrue(actual.getList().contains(toDoItem1));
        assertTrue(actual.getList().contains(toDoItem2));
    }

    @Test
    @Order(3)
    void shouldReturnToDoByID(){
        //GIVEN
        String url = "/todo/"+toDoItem1.getId();
        //WHEN
        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.getForEntity(url,ToDoItem.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ToDoItem actual = actualResponse.getBody();
        assertEquals(toDoItem1,actual);
    }

    @Test
    @Order(4)
    void shouldAdvanceToDo(){
        //GIVEN
        String url = "/todo/"+toDoItem1.getId();
        ToDoItem advancedItem = toDoItem1;
        advancedItem.setStatus(advancedItem.getStatus().advance());
        //WHEN
        testRestTemplate.put(url,toDoItem1);
        ResponseEntity<ToDoItem> actualResponse = testRestTemplate.getForEntity(url, ToDoItem.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ToDoItem actual = actualResponse.getBody();
        assertEquals(advancedItem,actual);
    }

    @Test
    @Order(5)
    void shouldDeleteToDoByID(){
        //GIVEN
        String url = "/todo/"+toDoItem1.getId();
        //WHEN
        testRestTemplate.delete(url);
        ResponseEntity<ListOfToDoItems> actualResponse = testRestTemplate.getForEntity("/todo", ListOfToDoItems.class);
        //THEN
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
        ListOfToDoItems actual = actualResponse.getBody();
        assert actual != null;
        assertFalse(actual.getList().contains(toDoItem1));
    }

}