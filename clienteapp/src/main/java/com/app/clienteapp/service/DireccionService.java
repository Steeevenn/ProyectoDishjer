package com.app.clienteapp.service;

import com.app.clienteapp.dto.ClienteDTO;
import com.app.clienteapp.dto.DireccionDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.Cliente;
import com.app.clienteapp.model.Direccion;
import com.app.clienteapp.repository.DireccionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    private final MapperInterface clienteMapper = MapperInterface.INSTANCE;

    public List<DireccionDTO> findAll() {
        return direccionRepository.findAll().stream()
                .map(clienteMapper::direccionToDireccionDTO)
                .collect(Collectors.toList());

    }

    // metodo para guardar un objeto de tipo Direcccion
    @Transactional
    public DireccionDTO save(DireccionDTO direccionDTO) {
        
    try {      
    Direccion direccion= clienteMapper.direccionDTOToDireccion(direccionDTO);
    Direccion saveDireccion = direccionRepository.save(direccion);
    
    return clienteMapper.direccionToDireccionDTO(saveDireccion);
    }catch(DataAccessException ex){
      throw new ServiceException("Error al guardar la dirección", ex);
    }

    }

    public void deleteById(Long id) {
        direccionRepository.deleteById(id);
    }

}
