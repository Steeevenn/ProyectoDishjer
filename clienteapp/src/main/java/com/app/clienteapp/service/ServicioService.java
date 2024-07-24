package com.app.clienteapp.service;

import com.app.clienteapp.dto.ServicioDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.Servicio;
import com.app.clienteapp.repository.ServicioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;
    
    private final MapperInterface clienteMapper = MapperInterface.INSTANCE;

    public List<ServicioDTO> findAll() {

        return servicioRepository.findAll().stream()
                .map(clienteMapper::servicioToServicioDTO)
                .collect(Collectors.toList());

    }
    
    public Optional <ServicioDTO> findById (@PathVariable Long id){
        
        Optional <Servicio> busquedaId = servicioRepository.findById(id);
        return busquedaId.map(clienteMapper::servicioToServicioDTO);
        
    }

    // metodo para guardar un objeto de tipo cliente
    @Transactional
    public ServicioDTO save(ServicioDTO servicioDTO) {
        
        Servicio servicio = clienteMapper.servicioDTOToServicio(servicioDTO);
        Servicio savedServicio = servicioRepository.save(servicio);
        
        return clienteMapper.servicioToServicioDTO(savedServicio);
    }

    public void deleteById(Long id) {
        servicioRepository.deleteById(id);
    }

}
