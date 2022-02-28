import Header from "../components/Header";
import ToDoGallery from "../components/ToDoGallery";

export default function MainPage(){
    return(
        <div className={'main'}>
            <Header/>
            <ToDoGallery/>
        </div>
    )
}