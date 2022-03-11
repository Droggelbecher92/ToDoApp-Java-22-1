import Header from "../components/Header";
import {useEffect, useState} from "react";
import {ToDoItem} from "../service/models";
import {useParams} from "react-router-dom";
import TodoDetail from "../components/TodoDetail";
import {getToDoById} from "../service/apiService";
import NavBar from "../components/NavBar";

export default function DetailsPage(){

    const [currentTodo, setCurrentTodo] = useState({} as ToDoItem)
    const [error , setError] = useState('')

    const id = useParams()


    useEffect(()=>{
        setError('')
        getToDoById(id.todoId ?? '')
            .then((data : ToDoItem) => setCurrentTodo(data))
            .catch(e => setError(e.message))
    },[id.todoId])



    return(
        <div className={'detailsPage'}>
            <NavBar/>
            <Header/>
            {error && <h1>{error}</h1>}
            {currentTodo
                ?
                <TodoDetail todo={currentTodo}/>
                :
                <p>loading....</p>
            }
        </div>
    )
}