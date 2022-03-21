import NavBar from "../components/NavBar";
import Header from "../components/Header";
import LoginForm from "../components/LoginForm";

export default function LoginPage(){
    return(
        <div className={'loginPage'}>
            <NavBar/>
            <Header/>
            <LoginForm/>
        </div>
    )
}