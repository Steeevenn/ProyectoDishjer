package com.app.clienteapp.repository;

import com.app.clienteapp.model.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TelefonoRepository  extends JpaRepository<Telefono, Long>{
    
}
