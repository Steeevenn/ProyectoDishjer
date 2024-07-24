import React, { useState } from 'react';
import axios from 'axios';

const SearchForm = ({ updateClientesList }) => {
  const [searchClienteId, setSearchClienteId] = useState('');
  const [searchClienteName, setSearchClienteName] = useState('');

  const handleSearch = async (e) => {
    e.preventDefault();

    try {
      let response;
      if (searchClienteId) {
        response = await axios.get(`http://localhost:8080/clientes/${searchClienteId}`);
      } else if (searchClienteName) {
        response = await axios.get(`http://localhost:8080/clientes/nombre/${searchClienteName}`);
      } else {
        response = await axios.get('http://localhost:8080/clientes');
      }

      if (response.data) {
        updateClientesList(response.data);
      }
    } catch (error) {
      console.error('Error al buscar cliente o servicios:', error);
      updateClientesList([]);
    }
  };

  return (
    <form className="form-inline my-2 my-lg-0" onSubmit={handleSearch}>
      <input
        className="form-control mr-sm-2"
        type="search"
        placeholder="Buscar por ID del Cliente"
        aria-label="Search"
        value={searchClienteId}
        onChange={(e) => setSearchClienteId(e.target.value)}
      />
      <input
        className="form-control mr-sm-2"
        type="search"
        placeholder="Buscar por Nombre del Cliente"
        aria-label="Search"
        value={searchClienteName}
        onChange={(e) => setSearchClienteName(e.target.value)}
      />
      <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
  );
};

export default SearchForm;
