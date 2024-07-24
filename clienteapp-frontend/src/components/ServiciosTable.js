/*import React from "react";
import { Link } from "react-router-dom";

const ServiciosTable = ({ servicios }) => {
  return (
    <table className="ui table table-custom mt-4">
      <thead>
        <tr>
          <th>ID del Cliente</th>
          <th>Nombre del Cliente</th>
          <th>ID del Servicio</th>
          <th>Fecha del Servicio</th>
          <th>Tipo de Servicio</th>
        </tr>
      </thead>
      <tbody>
        {servicios.map((cliente) =>
          cliente.equipos.map((equipo) =>
            equipo.servicios.map((servicio) => (
              <tr key={servicio.id}>
                <td>{cliente.id}</td>
                <td>{cliente.nombreCliente}</td>
                <td>{servicio.id}</td>
                <td>{servicio.fecha}</td>
                <td>{servicio.tipoServicio}</td>
              </tr>
            ))
          )
        )}
      </tbody>
    </table>
  );
};

export default ClientesTable;
*/