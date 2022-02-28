import {ToDoItem} from "../service/models";

interface ToDoCardProps{
    infos : ToDoItem
}

export default function ToDoCard({infos} : ToDoCardProps){
    return(
        <div className={'toDoCard'}>
            <h2>{infos.task}</h2>
            <p>{infos.description}</p>
            <button>{infos.status==='DONE'?'Löschen':'Weiter'}</button>
            <button>Bearbeiten</button>
            <button>{infos.status==='OPEN'?'Löschen':'Zurück'}</button>
        </div>
    )
}