import Header from "../components/Header";
import ToDoForm from "../components/ToDoForm";

export default function MainPage(){
    return(
        <div className={'main'}>
            <Header/>
            <ToDoForm/>
        </div>
    )
}