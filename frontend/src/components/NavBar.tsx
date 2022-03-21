import {useNavigate} from "react-router-dom";
import './NavBar.css'
import {useAuth} from "../auth/AuthProvider";

export default function NavBar(){
    const nav = useNavigate()
    const auth = useAuth()


    return(
        <div className={'navBar'}>
            <h3 onClick={()=> nav('/')}>Home</h3>
            <h3 onClick={auth.logout}>logout</h3>
        </div>
    )
}