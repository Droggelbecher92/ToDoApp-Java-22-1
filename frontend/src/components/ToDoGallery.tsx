import {useEffect, useState} from "react";
import {getAllTodos} from "../service/apiService";
import {ToDoItem} from "../service/models";
import './ToDoGallery.css'
import ToDoColumn from "./ToDoColumn";

export default function ToDoGallery(){
    const [todos, setTodos] = useState([] as Array<ToDoItem>)


    useEffect(()=>{
        getAllTodos()
            .then(data => setTodos(data))
            .then(() => console.log(todos))
    }, [])

    if (todos.length<1){
        return <h2>loading or empty</h2>
    }

    return(
         <div className={'toDoGallery'}>
                <ToDoColumn key={'OPEN'} state={'Offen'} items={todos.filter(item => item.status=='OPEN')}/>
                <ToDoColumn key={'IN_PROGRESS'} state={'In arbeit'} items={todos.filter(item => item.status=='IN_PROGRESS')}/>
                <ToDoColumn key={'DONE'} state={'Fertig'} items={todos.filter(item => item.status=='DONE')}/>
         </div>
    )
}