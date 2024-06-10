package com.app.clienteapp.repository;

import com.app.clienteapp.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
    
}
