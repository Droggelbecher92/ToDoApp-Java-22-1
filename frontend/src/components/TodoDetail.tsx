import {ToDoItem} from "../service/models";


interface ToDoDetailProps{
    todo : ToDoItem
}

export default function TodoDetail(props : ToDoDetailProps){
    return(
        <div className={'todoDetail'}>
            <h3>{props.todo.task}</h3>
            <p>{props.todo.description}</p>
            <ul>
                <li>{props.todo.status}</li>
                <li>{props.todo.id}</li>
            </ul>

        </div>
    )
}