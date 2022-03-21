import Header from "../components/Header";
import {useEffect, useState} from "react";
import {ToDoItem} from "../service/models";
import {useNavigate, useParams} from "react-router-dom";
import TodoDetail from "../components/TodoDetail";
import {getToDoById} from "../service/apiService";
import NavBar from "../components/NavBar";
import {useAuth} from "../auth/AuthProvider";

export default function DetailsPage(){

    const auth = useAuth()
    const nav = useNavigate()
    const [currentTodo, setCurrentTodo] = useState({} as ToDoItem)
    const [error , setError] = useState('')

    const id = useParams()

    if (!auth.token){
        nav("/login")
    }


    useEffect(()=>{
        setError('')
        getToDoById(id.todoId ?? '', auth.token)
            .then((data : ToDoItem) => setCurrentTodo(data))
            .catch(e => setError(e.message))
    },[id.todoId, auth.token])



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