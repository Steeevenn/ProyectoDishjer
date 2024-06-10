package com.app.clienteapp.service;

import com.app.clienteapp.model.Repuesto;
import com.app.clienteapp.repository.RepuestoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepuestoService {
    
    @Autowired
    private RepuestoRepository repuestoRepository;
    
    
    public List<Repuesto> findAll(){
    return repuestoRepository.findAll();
    }
    
    public Repuesto save(Repuesto repuesto){
    return repuestoRepository.save(repuesto);
    }
    
    public void deleteById(Long id){
    repuestoRepository.deleteById(id);
    }
    
    
}
