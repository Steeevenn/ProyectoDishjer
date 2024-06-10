package com.app.clienteapp.repository;

import com.app.clienteapp.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long>{
    
}
