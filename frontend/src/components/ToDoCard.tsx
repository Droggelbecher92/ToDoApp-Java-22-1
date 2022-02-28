import {ToDoItem} from "../service/models";
import {advanceTodo, deleteTodo, getAllTodos, updateTodo} from "../service/apiService";
import {Dispatch, SetStateAction} from "react";

interface ToDoCardProps{
    infos : ToDoItem
    update : Dispatch<SetStateAction<ToDoItem[]>>
}

export default function ToDoCard({infos, update} : ToDoCardProps){

    const statusRevert = () => {
        if (infos.status ==='OPEN'){
            deleteTodo(infos.id).then(()=>
                getAllTodos()
                .then(data => update(data)))
        } else if (infos.status ==='DONE') {
            infos.status='IN_PROGRESS'
            updateTodo(infos.id,infos).then(()=>
                getAllTodos()
                    .then(data => update(data)))
        } else {
            infos.status='OPEN'
            updateTodo(infos.id,infos).then(()=>
                getAllTodos()
                    .then(data => update(data)))
        }
    }

    const nextStatus = () => {
        advanceTodo(infos).then(()=>
            getAllTodos()
                .then(data => update(data)))
    }

    return(
        <div className={'toDoCard'}>
            <h2>{infos.task}</h2>
            <p>{infos.description}</p>
            <button onClick={()=>statusRevert()}>{infos.status==='OPEN'?'Löschen':'Zurück'}</button>
            <button>Bearbeiten</button>
            <button onClick={()=>nextStatus()}>{infos.status==='DONE'?'Löschen':'Weiter'}</button>
        </div>
    )
}