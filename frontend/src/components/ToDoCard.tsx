import {ToDoItem} from "../service/models";
import {advanceTodo, deleteTodo, getAllTodos, updateTodo} from "../service/apiService";
import {Dispatch, FormEvent, SetStateAction, useState} from "react";
import './TodoCard.css'
import {useNavigate} from "react-router-dom";

interface ToDoCardProps{
    infos : ToDoItem
    update : Dispatch<SetStateAction<ToDoItem[]>>
}

export default function ToDoCard({infos, update} : ToDoCardProps){

    const [task , setTask] = useState(infos.task)
    const [desc, setDesc] = useState(infos.description)
    const [editor, setEditor] = useState(false)
    const nav = useNavigate()

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

    const toDetails = () => {
        nav(`/${infos.id}`)
    }

    const nextStatus = () => {
        advanceTodo(infos).then(()=>
            getAllTodos()
                .then(data => update(data)))
    }

    const changeToDo = (ev : FormEvent<HTMLFormElement>) =>{
        ev.preventDefault()
        updateTodo(infos.id, {
            id : infos.id,
            task: task,
            description : desc,
            status:  infos.status
        }
        )
            .then(()=>
                getAllTodos()
                    .then(data => update(data)))
            .catch(e => console.log('Blöd...'))
        setEditor(false)
    }

    return(<div onClick={() => toDetails()}>
        { editor
            ?
            <form onSubmit={ev =>changeToDo(ev)} className={'toDoCard'}>
                <input type={'text'} className={'card_task'} value={task} onChange={ev => setTask(ev.target.value)}/>
                <input type={'text'} className={'card_text'} value={desc} onChange={ev => setDesc(ev.target.value)}/>
                <div className={'card_btn'}>
                    <button onClick={() => statusRevert()} disabled>{infos.status === 'OPEN' ? '<Löschen' : '<Zurück'}</button>
                    <button>Absenden</button>
                    <button onClick={() => nextStatus()} disabled>{infos.status === 'DONE' ? 'Löschen>' : 'Weiter>'}</button>
                </div>
            </form>
            :
            <div className={'toDoCard'}>
                <h2 data-testid="the-task" className={'card_task'}>{infos.task}</h2>
                <p data-testid="the-desc" className={'card_text'}>{infos.description}</p>
                <div className={'card_btn'}>
                    <button data-testid="btn-back" onClick={() => statusRevert()}>{infos.status === 'OPEN' ? '<Löschen' : '<Zurück'}</button>
                    <button onClick={() => setEditor(true)}>Bearbeiten</button>
                    <button data-testid="btn-next" onClick={() => nextStatus()}>{infos.status === 'DONE' ? 'Löschen>' : 'Weiter>'}</button>
                </div>
            </div>
        }
    </div>)
}