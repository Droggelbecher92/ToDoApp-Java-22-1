import {ReactNode, useContext, useEffect, useState} from "react";
import AuthContext from "./AuthContext";
import {loginUser} from "../service/apiService";
import {useNavigate} from "react-router-dom";
import jwt_decode from 'jwt-decode';
import {UserInterface} from "../interfaces/interfaces";

export default function AuthProvider({children}:{children :ReactNode}) {

    const [token , setToken] = useState('')
    const [user, setUser] = useState({} as UserInterface)
    const nav = useNavigate()


    useEffect(()=>{
        if (token){
            nav("/")
        } else {
            nav("/login")
        }
    }, [token, nav])

    const login = (username: string, password : string) => {
        return loginUser({username:username, password:password})
            .then(data => {
                setToken(data.token)
                setUser(jwt_decode(data.token))
            })
    }

    const logout = () => {
        setToken('')
    }

    return <AuthContext.Provider value={{token, user, login, logout}} >{children}</AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext)