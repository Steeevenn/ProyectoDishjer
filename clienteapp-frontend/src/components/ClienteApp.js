import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/clientes';


const ClienteApp = () => {
  const [clientes, setClientes] = useState([]);
  const [nombreCliente, setNombreCliente] = useState('');

  // Obtener todos los clientes
  const obtenerClientes = async () => {
    try {
      const response = await axios.get(BASE_URL);
      setClientes(response.data);
    } catch (error) {
      console.error('Error al obtener clientes:', error);
    }
  };

  // Agregar un nuevo cliente
  const agregarCliente = async () => {
    try {
      const response = await axios.post(BASE_URL,{ nombre: nombreCliente });
      setClientes([...clientes, response.data]);
      setNombreCliente('');
    } catch (error) {
      console.error('Error al agregar cliente:', error);
    }
  };

  // Eliminar un cliente por ID
  const eliminarCliente = async (idCliente) => {
    try {
      await axios.delete(`${BASE_URL}/${idCliente}`);
      setClientes(clientes.filter((cliente) => cliente.id !== idCliente));
    } catch (error) {
      console.error('Error al eliminar cliente:', error);
    }
  };

  useEffect(() => {
    obtenerClientes();
  }, []); // Se ejecuta solo una vez al cargar el componente

  return (
    <div>
      <h1>Clientes</h1>
      <ul>
        {clientes.map((cliente) => (
          <li key={cliente.id}>
            {cliente.nombre}{' '}
            <button onClick={() => eliminarCliente(cliente.id)}>Eliminar</button>
          </li>
        ))}
      </ul>
      <div>
        <input
          type="text"
          value={nombreCliente}
          onChange={(e) => setNombreCliente(e.target.value)}
          placeholder="Nombre del cliente"
        />
        <button onClick={agregarCliente}>Agregar Cliente</button>
      </div>
    </div>
  );
};

export default ClienteApp;
