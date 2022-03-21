import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import DetailsPage from "./pages/DetailsPage";
import LoginPage from "./pages/LoginPage";


function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path={'/'} element={<MainPage/>}/>
                    <Route path={'/login'} element={<LoginPage/>}/>
                    <Route path={'/:todoId'} element={<DetailsPage/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
