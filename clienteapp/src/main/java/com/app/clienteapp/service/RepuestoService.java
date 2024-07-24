package com.app.clienteapp.service;

import com.app.clienteapp.dto.RepuestoDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.Repuesto;
import com.app.clienteapp.repository.RepuestoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepuestoService {
    
    @Autowired
    private RepuestoRepository repuestoRepository;
    
        private final MapperInterface clienteMapper = MapperInterface.INSTANCE;

    
    public List<RepuestoDTO> findAll(){
        
    return repuestoRepository.findAll().stream()
            .map(clienteMapper::repuestoToRepuestoDTO)
            .collect(Collectors.toList());
    }
    
    @Transactional
    public RepuestoDTO save(RepuestoDTO repuestoDTO){
        Repuesto repuesto = clienteMapper.repuestoDTOToRepuesto(repuestoDTO);
        Repuesto repuestoSaved = repuestoRepository.save(repuesto);
    return clienteMapper.repuestoToRepuestoDTO(repuestoSaved);
    
    }
    
    public void deleteById(Long id){
    repuestoRepository.deleteById(id);
    }
    
    
}
