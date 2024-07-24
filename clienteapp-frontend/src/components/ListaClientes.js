import React, { useState } from "react";
import axios from "axios";
import DownloadPdfButton from "./DownloadPdfButton";

const ListaClientes = ({
  clientes,
  filteredClientes,
  filteredServices,
  errorMessage,
}) => {
  const renderClientes = () => {
    // Usa filteredClientes si está definido y no está vacío, de lo contrario usa todos los clientes
    const clientesToRender =
      filteredClientes.length > 0 ? filteredClientes : clientes;

    return (
      <>
        <table className="ui table table-custom mt-4   ">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre del Cliente</th>
              <th>ID del Servicio</th>
              <th>Fecha del Servicio</th>
              <th>Tipo de Servicio</th>
              <th>PDF</th>
            </tr>
          </thead>
          <tbody>
            {filteredServices.length > 0
              ? filteredServices.map((servicio) => {
                  const cliente = clientes.find(
                    (c) => c.id === servicio.clienteId
                  );
                  const clienteConPdf = clientes.find(
                    (c) => c.nombreCliente === cliente.nombreCliente && c.pdf
                  );
                  return (
                    <tr key={servicio.id}>
                      <td>{servicio.clienteId}</td>
                      <td>{servicio.nombreCliente}</td>
                      <td>{servicio.id}</td>
                      <td>
                        <a href="#" className="colorFecha">
                          {servicio.fecha}
                        </a>
                      </td>
                      <td>{servicio.tipoServicio}</td>
                      <td>
                        <td>
                          <DownloadPdfButton
                            clienteId={cliente.id}
                            disabled={clienteConPdf}
                          />
                        </td>
                      </td>
                    </tr>
                  );
                })
              : clientesToRender.map((cliente) =>
                  cliente.equipos.map((equipo) =>
                    equipo.servicios.map((servicio) => {
                      const clienteConPdf = clientes.find(
                        (c) =>
                          c.nombreCliente === cliente.nombreCliente && c.pdf
                      );
                      return (
                        <tr key={servicio.id}>
                          <td>
                            {cliente.id}
                          </td>
                          <td>{cliente.nombreCliente}</td>
                          <td>{servicio.id}</td>
                          <td>
                            <a className="colorFecha">{servicio.fecha}</a>
                          </td>
                          <td>{servicio.tipoServicio}</td>
                          <td>
                            <DownloadPdfButton
                              clienteId={cliente.id}
                              disabled={!clienteConPdf}
                            />
                          </td>
                        </tr>
                      );
                    })
                  )
                )}
          </tbody>
        </table>

        {errorMessage && (
          <div className="alert alert-danger mt-3">{errorMessage}</div>
        )}
      </>
    );
  };

  return <div>{renderClientes()}</div>;
};

export default ListaClientes;
