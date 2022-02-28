import {ToDoItem} from "../service/models";
import {advanceTodo, deleteTodo, updateTodo} from "../service/apiService";

interface ToDoCardProps{
    infos : ToDoItem
}

export default function ToDoCard({infos} : ToDoCardProps){

    const revertTodo = () => {
        if (infos.status ==='OPEN'){
            deleteTodo(infos.id)
                .then(() => console.log('WOOP'))
        } else if (infos.status ==='DONE') {
            infos.status='IN_PROGRESS'
            updateTodo(infos.id,infos)
                .then(() => console.log('WOOP'))
        } else {
            infos.status='OPEN'
            updateTodo(infos.id,infos)
                .then(() => console.log('WOOP'))
        }
    }

    return(
        <div className={'toDoCard'}>
            <h2>{infos.task}</h2>
            <p>{infos.description}</p>
            <button onClick={()=>revertTodo()}>{infos.status==='OPEN'?'Löschen':'Zurück'}</button>
            <button>Bearbeiten</button>
            <button onClick={()=>advanceTodo(infos)}>{infos.status==='DONE'?'Löschen':'Weiter'}</button>
        </div>
    )
}