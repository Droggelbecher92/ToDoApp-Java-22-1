import Header from "../components/Header";
import ToDoForm from "../components/ToDoForm";
import ToDoGallery from "../components/ToDoGallery";

export default function MainPage(){
    return(
        <div className={'main'}>
            <Header/>
            <ToDoForm/>
            <ToDoGallery/>
        </div>
    )
}