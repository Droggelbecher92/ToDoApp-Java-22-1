export interface Credentials {
    username : string,
    password : string
}

export interface CredentialsRegister {
    username : string,
    password : string,
    passwordAgain : string
}

export interface AuthInterface {
    token : string,
    login : (username: string, password: string) => Promise<void>,
    logout: () => void
}