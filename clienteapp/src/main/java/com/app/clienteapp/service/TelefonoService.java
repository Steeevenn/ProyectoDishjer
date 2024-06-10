package com.app.clienteapp.service;

import com.app.clienteapp.model.Telefono;
import com.app.clienteapp.repository.TelefonoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefonoService {

@Autowired
private TelefonoRepository telefonoRepository;

    public List<Telefono> findAll(){
    return telefonoRepository.findAll();
    }
    
    public Telefono save(Telefono telefono){
    return telefonoRepository.save(telefono);
    }
    
    public void deleteById(Long id){
    telefonoRepository.deleteById(id);
    }
    
}
