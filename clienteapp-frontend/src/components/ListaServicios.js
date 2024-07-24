import React, { useEffect, useState } from "react";
import axios from "axios";

const ListaServicios = () => {
  const [clientes, setClientes] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/clientesService"
        );
        setClientes(response.data);
      } catch (error) {
        console.error("Error al obtener los clientes:", error);
        setErrorMessage("Error al obtener los clientes.");
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
      <table className="ui table table-custom mt-4  ">
        <thead>
          <tr>
            <th>Nombre Cliente</th>
            <th>Dirección</th>
            <th>Teléfono </th>
            <th>Fecha de Solicitud </th>
            <th>Tipo de Servicio</th>
            <th>Observaciones</th>
          </tr>
        </thead>
        <tbody>
          {clientes.map((cliente) =>
            cliente.solicitudes.map((solicitud) => (
              <tr key={solicitud.id}>
                <td>{cliente.nombreCliente}</td>
                <td>{cliente.direccion}</td>
                <td>{cliente.telefono}</td>
                <td>
                  <a href="#" className="colorFecha">
                    {solicitud.fechaServicio}
                  </a>
                </td>
                <td>{solicitud.tipoServicio}</td>
                <td className="observaciones-column">
                  {solicitud.observaciones}
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ListaServicios;
