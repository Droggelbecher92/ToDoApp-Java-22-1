import {ToDoItem} from "../service/models";
import ToDoCard from "./ToDoCard";
import {Dispatch, SetStateAction} from "react";

interface ToDoColumnProps{
    items : Array<ToDoItem>
    state : string
    update : Dispatch<SetStateAction<ToDoItem[]>>
}

export default function ToDoColumn(props : ToDoColumnProps){
    return(
        <div className={'toDoColumn'}>
            <h1>{props.state}</h1>
            {props.items.map(todo => <ToDoCard update={props.update} key={todo.id} infos={todo}/>)}
        </div>
    )
}