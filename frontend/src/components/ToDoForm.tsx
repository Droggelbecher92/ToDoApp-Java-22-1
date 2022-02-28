import {FormEvent, useState} from "react";
import {postNewToDo} from "../service/apiService";

export default function ToDoForm(){

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
            })
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