import {ToDoItem} from "./models";

export const getAllTodos = () => {
    return fetch(`http://localhost:8080/api/todo`)
        .then(response => response.json())
}

export const postNewToDo = (task: string, descript: string) => {
    return fetch(`http://localhost:8080/api/todo`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'task':task, 'description':descript})
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

export const advanceTodo = (todo : ToDoItem) => {
    return fetch(`http://localhost:8080/api/todo`,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todo)
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

export const updateTodo = (id: string, todo : ToDoItem) => {
    return fetch(`http://localhost:8080/api/todo/${id}`,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todo)
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

export const deleteTodo = (id: string) => {
    return fetch(`http://localhost:8080/api/todo/${id}`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}