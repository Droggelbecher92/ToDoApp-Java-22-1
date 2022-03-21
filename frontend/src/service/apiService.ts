import {ToDoItem} from "./models";
import {Credentials, CredentialsRegister} from "../interfaces/interfaces";

export const registerNewUser = ({username, password, passwordAgain} : CredentialsRegister) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/user`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'username':username, 'password':password, 'passwordAgain':passwordAgain})
    })
        .then(response => response.json())
}

export const loginUser = ({username, password} : Credentials) =>{
    return fetch(`${process.env.REACT_APP_BASE_URL}/auth`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'username':username, 'password':password})
    })
        .then(reponse => reponse.json())
}

export const getAllTodos = (token: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo`,{
        headers: {
            Authorization: `Bearer ${token}`
        },
    })
        .then(response => response.json())
}

export const postNewToDo = (task: string, descript: string, token: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo`, {
        method: 'POST',
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'task':task, 'description':descript})
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

export const advanceTodo = (todo : ToDoItem, token: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo`,{
        method: 'PUT',
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todo)
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

export const updateTodo = (id: string, todo : ToDoItem, token: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo/${id}`,{
        method: 'PUT',
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todo)
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

export const getToDoById = (id : string, token: string) => {
     return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo/${id}`,{
         headers: {
             Authorization: `Bearer ${token}`
         }
     })
        .then(response => {
            if (response.ok){
              return response.json()
            } else {
                throw Error("Kein ToDo mit der id "+id+" gefunden!")
            }
        })
}

export const deleteTodo = (id: string, token: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo/${id}`,{
        method: 'DELETE',
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

