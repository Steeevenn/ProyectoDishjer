package com.app.clienteapp.service;

import com.app.clienteapp.dto.SolicitudServiceDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.SolicitudServicioTecnico;
import com.app.clienteapp.repository.SolicitudServiceRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudServicioTecService {

    @Autowired
    private SolicitudServiceRepository solicitudServiceRepository;
    
    
    private final MapperInterface clienteMapper = MapperInterface.INSTANCE;
    
    
    // Metodo para vizualizar todas las solcitudes
    
    public List<SolicitudServiceDTO> findAll(){
    
        return  solicitudServiceRepository.findAll().stream()
                .map(clienteMapper::solicitudServicioTecnicoToSolicitudServiceDTO)
                .collect(Collectors.toList());
                
    }
    
    @Transactional
    public SolicitudServiceDTO save(SolicitudServiceDTO solicitudServiceDTO){
    
        SolicitudServicioTecnico SolicitudServicioService = clienteMapper.solicitudServiceDTOToSolicitudServicioTecnico(solicitudServiceDTO);
        SolicitudServicioTecnico savedCliente = solicitudServiceRepository.save(SolicitudServicioService);
        
        return clienteMapper.solicitudServicioTecnicoToSolicitudServiceDTO(savedCliente);
        
        
    }
    
    
    // Metodo para borrar las solicitudes de servicio
    public void DeleteById(Long id ){
    
        solicitudServiceRepository.deleteById(id);
    }
    
    
    
    
    
    
}
    