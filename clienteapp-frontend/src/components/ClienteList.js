import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ClientesList = () => {
    const [clientes, setClientes] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/clientes');
              setClientes(response.data);
            } catch (error) {
                console.error('Error al obtener los datos:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <div className="container mt-5">
            <h2>Lista de Clientes</h2>
            <ul className="list-group">
                {Array.isArray(clientes) && clientes.length > 0 ? (
                    clientes.map(cliente => (
                        <li key={cliente.id} className="list-group-item">
                            {cliente.nombreCliente}
                        </li>
                    ))
                ) : (
                    <li className="list-group-item">No hay clientes disponibles</li>
                )}
            </ul>
        </div>
    );
};

export default ClientesList;
