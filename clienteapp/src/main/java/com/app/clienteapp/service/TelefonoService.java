package com.app.clienteapp.service;

import com.app.clienteapp.dto.TelefonoDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.Telefono;
import com.app.clienteapp.repository.TelefonoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefonoService {

@Autowired
private TelefonoRepository telefonoRepository;

private final MapperInterface  clienteMapper  = MapperInterface.INSTANCE;


    public List<TelefonoDTO> findAll(){
    return telefonoRepository.findAll().stream()
            .map(clienteMapper::telefonoToTelefonoDTO)
            .collect(Collectors.toList());
    }
    @Transactional
    public TelefonoDTO save(TelefonoDTO telefonoDTO){
  Telefono telefono = clienteMapper.telefonoDTOToTelefono(telefonoDTO);
  Telefono savedTelefono = telefonoRepository.save(telefono);
  return clienteMapper.telefonoToTelefonoDTO(savedTelefono);
        
    }
    
    public void deleteById(Long id){
    telefonoRepository.deleteById(id);
    }
    
}
