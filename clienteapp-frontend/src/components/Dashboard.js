import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../App.css";
import ListaServicios from "./ListaServicios";
import ListaClientes from "./ListaClientes";

const Dashboard = () => {
  const [clientes, setClientes] = useState([]);
  const [searchParams, setSearchParams] = useState({
    id: "",
    nombre: "",
  });
  const [filteredClientes, setFilteredClientes] = useState([]);
  const [filteredServices, setFilteredServices] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [showClientes, setShowClientes] = useState(true); // Estado para controlar qué componente mostrar
  const [downloadUrl, setDownloadUrl] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/clientes");
        setClientes(response.data);

        // Inicialmente, mostramos todos los servicios sin filtrar
        const servicios = [];
        response.data.forEach((cliente) => {
          cliente.equipos.forEach((equipo) => {
            equipo.servicios.forEach((servicio) => {
              servicios.push({
                ...servicio,
                nombreCliente: cliente.nombreCliente,
                clienteId: cliente.id,
              });
            });
          });
        });
        setFilteredServices(servicios);
      } catch (error) {
        console.error("Error al obtener los datos:", error);
        setErrorMessage("Error al obtener los datos.");
      }
    };

    fetchData();
  }, []);

  const handleSearch = async (e) => {
    e.preventDefault();
    setErrorMessage(""); // Limpiar cualquier mensaje de error previo
    try {
      const { id, nombre } = searchParams;
      const filtered = clientes.filter(
        (cliente) =>
          (id === "" || cliente.id.toString().includes(id)) &&
          (nombre === "" ||
            cliente.nombreCliente.toLowerCase().includes(nombre.toLowerCase()))
      );

      setFilteredClientes(filtered);

      const servicios = [];
      filtered.forEach((cliente) => {
        cliente.equipos.forEach((equipo) => {
          equipo.servicios.forEach((servicio) => {
            servicios.push({
              ...servicio,
              nombreCliente: cliente.nombreCliente,
              clienteId: cliente.id,
            });
          });
        });
      });

      setFilteredServices(servicios);
    } catch (error) {
      console.error("Error al buscar cliente o servicios:", error);
      setFilteredClientes([]);
      setFilteredServices([]);
      let errorMessage = "Error al buscar el cliente o los servicios";

      if (error.response && error.response.data) {
        if (typeof error.response.data === "string") {
          errorMessage = `Error: ${error.response.data}`;
        } else if (error.response.data.message) {
          errorMessage = `Error: ${error.response.data.message}`;
        } else if (error.response.data.error) {
          errorMessage = `Error: ${error.response.data.error}`;
        } else {
          errorMessage = `Error: ${JSON.stringify(error.response.data)}`;
        }
      } else if (error.message) {
        errorMessage = `Error: ${error.message}`;
      }

      setErrorMessage(errorMessage); // Establecer el mensaje de error
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setSearchParams((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleClientesClick = () => {
    setShowClientes(true);
  };

  const handleServiciosClick = () => {
    setShowClientes(false);
  };

  return (
    <div className="box-wrapper">
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a className="navbar-brand" href="#">
          Inicio
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <a
                className={`nav-link ${showClientes ? "active" : ""}`}
                href="#"
                onClick={handleClientesClick}
              >
                Clientes Dishjer <span className="sr-only">(current)</span>
              </a>
            </li>
            <li className="nav-item">
              <a
                className={`nav-link ${showClientes ? "" : "active"}`}
                href="#"
                onClick={handleServiciosClick}
              >
                Servicios Pendientes <span className="sr-only">(current)</span>
              </a>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/service-form">
                Formulario
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/Request-service">
                Solicitud Servicio
              </Link>
            </li>
          </ul>
          <form className="form-inline my-2 my-lg-0" onSubmit={handleSearch}>
            <input
              className="form-control mr-sm-2"
              type="search"
              placeholder="Buscar por ID"
              aria-label="Search"
              name="id"
              value={searchParams.id}
              onChange={handleInputChange}
            />

            <input
              className="form-control mr-sm-2"
              type="search"
              placeholder="Buscar por Nombre"
              aria-label="Search"
              name="nombre"
              value={searchParams.nombre}
              onChange={handleInputChange}
            />
            <button
              className="btn btn-outline-success my-2 my-sm-0"
              type="submit"
            >
              Buscar
            </button>
          </form>
        </div>
      </nav>

      <div className="container-fluid table-wrapper">
        {showClientes ? (
          <ListaClientes
            clientes={clientes}
            filteredClientes={filteredClientes}
            filteredServices={filteredServices}
            errorMessage={errorMessage}
          />
        ) : (
          <ListaServicios />
        )}
        {errorMessage && (
          <div className="alert alert-danger mt-3">{errorMessage}</div>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
