import {FormEvent, useState} from "react";
import {registerNewUser} from "../service/apiService";
import {useAuth} from "../auth/AuthProvider";

export default function LoginForm(){
    const [loginUsername, setLoginUsername] = useState('')
    const [loginPassword, setLoginPassword] = useState('')
    const [registerUsername, setRegisterUsername] = useState('')
    const [registerPasswordOne, setRegisterPasswordOne] = useState('')
    const [registerPasswordTwo, setRegisterPasswordTwo] = useState('')
    const [error, setError] = useState('')

    const auth = useAuth()


    const handleLogin = (event : FormEvent) => {
        event.preventDefault()
        setError('')
        auth.login(loginUsername, loginPassword)
            .catch(er => setError(er.response.message))
    }

    const handleRegister = (event : FormEvent) => {
        event.preventDefault()
        setError('')
        if (!(registerPasswordOne===registerPasswordTwo)){
            setError('Passwörter nicht gleich')
        } else {
            registerNewUser({username: registerUsername, password: registerPasswordOne, passwordAgain: registerPasswordTwo})
                .catch(er => {
                    if (er.status === 400){
                        console.log("kdaljshg")
                        setError('Anmeldedaten ungültig')
                    } else {
                        setError("Etwas ist schief gelaufen")
                    }
                    })
        }
    }


    return(
        <div className={'loginForm'}>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <input type="text" placeholder={'Nutzername'} value={loginUsername} onChange={ev => setLoginUsername(ev.target.value)}/>
                <input type='password' placeholder={'Passwort'} value={loginPassword} onChange={ev => setLoginPassword(ev.target.value)}/>
                <button type={'submit'}>Login</button>
            </form>
            <h2>Register</h2>
            <form onSubmit={handleRegister}>
                <input type="text" placeholder={'Nutzername'} value={registerUsername} onChange={ev => setRegisterUsername(ev.target.value)}/>
                <input type='password' placeholder={'Passwort'} value={registerPasswordOne} onChange={ev => setRegisterPasswordOne(ev.target.value)}/>
                <input type='password' placeholder={'Passwort wiederholen'} value={registerPasswordTwo} onChange={ev => setRegisterPasswordTwo(ev.target.value)}/>
                <button type={'submit'}>Register</button>
            </form>
            {error && <h2>{error}</h2>}
        </div>
    )
}