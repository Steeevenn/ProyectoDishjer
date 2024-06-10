package com.app.clienteapp.service;

import com.app.clienteapp.model.Servicio;
import com.app.clienteapp.repository.ServicioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    // metodo para guardar un objeto de tipo cliente
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void deleteById(Long id) {
        servicioRepository.deleteById(id);
    }

}
