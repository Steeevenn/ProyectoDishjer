package com.app.clienteapp.repository;
import com.app.clienteapp.model.SolicitudServicioTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudServiceRepository extends JpaRepository<SolicitudServicioTecnico, Long>{
    
}
