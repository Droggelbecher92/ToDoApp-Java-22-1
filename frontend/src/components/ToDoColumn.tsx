import {ToDoItem} from "../service/models";
import ToDoCard from "./ToDoCard";

interface ToDoColumnProps{
    items : Array<ToDoItem>
    state : string
}

export default function ToDoColumn(props : ToDoColumnProps){
    return(
        <div className={'toDoColumn'}>
            <h1>{props.state}</h1>
            {props.items.map(todo => <ToDoCard key={todo.id} infos={todo}/>)}
        </div>
    )
}