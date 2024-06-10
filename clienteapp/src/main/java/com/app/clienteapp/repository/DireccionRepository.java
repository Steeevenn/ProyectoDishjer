package com.app.clienteapp.repository;

import com.app.clienteapp.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
    
}
