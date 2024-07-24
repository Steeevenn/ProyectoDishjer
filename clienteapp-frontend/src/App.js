import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ServiceForm from "./components/ServiceForm";
import ClientesList from "./components/Dashboard";
import ClientesTable from "./components/ClientesTable";
import SearchForm from "./components/SearchForm";
import RequestService from "./components/RequestService";

import Home from "./components/Home";

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<ServiceForm />} />
          <Route path="/clientes" element={<ClientesList />} />
          <Route path="/service-form" element={<ServiceForm />} />
          <Route path="/Home" element={<Home />} />
          <Route path="/clientes-table" element={<ClientesTable />} />
          <Route path="/search-form" element={<SearchForm />} />
          <Route path="/request-service" element={<RequestService />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
