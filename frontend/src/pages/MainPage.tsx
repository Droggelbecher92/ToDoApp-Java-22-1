import Header from "../components/Header";
import ToDoGallery from "../components/ToDoGallery";
import NavBar from "../components/NavBar";

export default function MainPage(){
    return(
        <div className={'main'}>
            <NavBar/>
            <Header/>
            <ToDoGallery/>
        </div>
    )
}