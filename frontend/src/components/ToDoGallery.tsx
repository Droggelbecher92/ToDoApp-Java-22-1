import {useEffect, useState} from "react";
import {getAllTodos} from "../service/apiService";
import {ToDoItem} from "../service/models";
import './ToDoGallery.css'
import ToDoColumn from "./ToDoColumn";
import ToDoForm from "./ToDoForm";

export default function ToDoGallery(){
    const [todos, setTodos] = useState([] as Array<ToDoItem>)
    const [error, setError] = useState('')


    useEffect(()=>{
        setError('')
        getAllTodos()
            .then(data => setTodos(data))
            .catch(e => setError(e.message))
    }, [])

    if (todos.length<1){
        return (<div>
            <ToDoForm update={setTodos}/>
        </div>)
    }

    return(
         <div>
             <ToDoForm update={setTodos}/>
             {error && <h1>{error}</h1>}
             <div className={'toDoGallery'}>
                 <ToDoColumn key={'OPEN'} update={setTodos} state={'Offen'} items={todos.filter(item => item.status==='OPEN')}/>
                 <ToDoColumn key={'IN_PROGRESS'} update={setTodos} state={'In arbeit'} items={todos.filter(item => item.status==='IN_PROGRESS')}/>
                 <ToDoColumn key={'DONE'} update={setTodos} state={'Fertig'} items={todos.filter(item => item.status==='DONE')}/>
             </div>
         </div>
    )
}