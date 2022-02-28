import {Dispatch, FormEvent, SetStateAction, useState} from "react";
import {getAllTodos, postNewToDo} from "../service/apiService";
import {ToDoItem} from "../service/models";

interface ToDoFormProps{
    update : Dispatch<SetStateAction<ToDoItem[]>>
}

export default function ToDoForm({update}: ToDoFormProps){

    const [task, setTask] = useState('');
    const [description, setDescription] = useState('');

    const handleTaskChange = (text : string)  => setTask(text)
    const handleDescriptionChange = (text : string)  => setDescription(text)

    const createToDo = (event : FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        postNewToDo(task, description)
            .then(() => {
                setDescription('')
                setTask('')
            }).then(()=>
            getAllTodos()
                .then(data => update(data)))
    }

    return(
        <div className={'toDoForm'}>
            <form onSubmit={ev => createToDo(ev)}>
                <input type="text" placeholder={'ToDo'} value={task} onChange={ev => handleTaskChange(ev.target.value)}/>
                <input type="text" placeholder={'Beschreibung'} value={description} onChange={ev => handleDescriptionChange(ev.target.value)}/>
                <button type='submit'>senden</button>
            </form>
        </div>
    )
}