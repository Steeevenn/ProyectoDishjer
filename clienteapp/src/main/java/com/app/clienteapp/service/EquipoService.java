package com.app.clienteapp.service;

import com.app.clienteapp.model.Equipo;
import com.app.clienteapp.repository.EquipoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {
    
       @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    // metodo para guardar un objeto de tipo cliente
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void deleteById(Long id) {
        equipoRepository.deleteById(id);
    }

}
