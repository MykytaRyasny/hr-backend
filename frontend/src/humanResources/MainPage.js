import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'; // importamos la hoja de estilos de Bootstrap

function MainPage() {
    return (
        <div className="container">
            <h1 className="text-center my-5">Bienvenido a mi sitio web</h1>
            <div className="row">
                <div className="col-md-6">
                    <button className="btn btn-primary btn-lg btn-block my-3">Botón 1</button>
                </div>
                <div className="col-md-6">
                    <button className="btn btn-secondary btn-lg btn-block my-3">Botón 2</button>
                </div>
            </div>
            <div className="row">
                <div className="col-md-4">
                    <button className="btn btn-success btn-lg btn-block my-3">Botón 3</button>
                </div>
                <div className="col-md-4">
                    <button className="btn btn-warning btn-lg btn-block my-3">Botón 4</button>
                </div>
                <div className="col-md-4">
                    <button className="btn btn-danger btn-lg btn-block my-3">Botón 5</button>
                </div>
            </div>
        </div>
    );
}

export default MainPage;