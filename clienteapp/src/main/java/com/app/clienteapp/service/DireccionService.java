package com.app.clienteapp.service;

import com.app.clienteapp.model.Direccion;
import com.app.clienteapp.repository.DireccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    
    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    // metodo para guardar un objeto de tipo cliente
    public Direccion save(Direccion contacto) {
        return direccionRepository.save(contacto);
    }

    public void deleteById(Long id) {
        direccionRepository.deleteById(id);
    }

}
