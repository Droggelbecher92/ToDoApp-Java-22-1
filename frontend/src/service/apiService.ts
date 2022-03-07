import {ToDoItem} from "./models";

export const getAllTodos = () => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo`)
        .then(response => response.json())
}

export const postNewToDo = (task: string, descript: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo`, {
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
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo`,{
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
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo/${id}`,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todo)
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}







export const getToDoById = (id : string) => {
     return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo/${id}`)
        .then(response => {
            if (response.ok){
              return response.json()
            } else {
                throw Error("Kein ToDo mit der id "+id+" gefunden!")
            }
        })
}




























export const deleteTodo = (id: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/todo/${id}`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}

