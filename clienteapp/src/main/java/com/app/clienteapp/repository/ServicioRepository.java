package com.app.clienteapp.repository;

import com.app.clienteapp.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    
}
