import {useNavigate} from "react-router-dom";
import './NavBar.css'

export default function NavBar(){
    const nav = useNavigate()

    return(
        <div className={'navBar'}>
            <h3 onClick={()=> nav('/')}>Home</h3>
        </div>
    )
}