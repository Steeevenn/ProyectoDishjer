// src/App.js
/*import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ClienteList from './components/ClienteList';
import ClienteForm from './components/ClienteForm';
import './App.css';

function App() {
    const [clientes, setClientes] = useState([]);
    const [clienteToEdit, setClienteToEdit] = useState(null);

    useEffect(() => {
        fetchClientes();
    }, []);

    const fetchClientes = () => {
        axios.get('/clientes')
            .then(response => {
                setClientes(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the clientes!", error);
            });
    };

    return (
        <div className="App">
            <h1>Gestión de Clientes</h1>
            <ClienteForm clienteToEdit={clienteToEdit} fetchClientes={fetchClientes} />
            <ClienteList clientes={clientes} setClientes={setClientes} onEdit={setClienteToEdit} />
        </div>
    );
}

export default App;
*/

import React from 'react';
import ServiceForm from './components/ServiceForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import ClientesList from './components/ClienteList';

const App = () => {
    return (
        <div className="App">
            <ServiceForm />
            <ClientesList />
        </div>
    );
};

export default App;
