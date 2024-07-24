package com.app.clienteapp.service;

import com.app.clienteapp.model.ClienteService;
import com.app.clienteapp.dto.ClienteServiceDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.repository.ClienteServiceRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteSolicitudService {
    
    @Autowired
    private ClienteServiceRepository clienteServiceRepository;
    
    private final MapperInterface clienteMapper = MapperInterface.INSTANCE;

    public List<ClienteServiceDTO> findall(){
    
        return clienteServiceRepository.findAll().stream()
             .map(clienteMapper::clienteServiceToClienteServiceDTO)
             .collect(Collectors.toList()) ;


              
    }



     @Transactional
    // metodo para guardar un objeto de tipo ClienteServiceDTO
    public ClienteServiceDTO save(ClienteServiceDTO clienteServiceDTO){
      
        ClienteService clienteService = clienteMapper.clienteServiceDTOToClienteService(clienteServiceDTO);
        ClienteService savedCliente = clienteServiceRepository.save(clienteService);
        
        
    return  clienteMapper.clienteServiceToClienteServiceDTO(savedCliente);
            
    }
    
    // Metodo para borrar Cliente que solicita un servicio
    public void DeleteClienteService(Long id){
    clienteServiceRepository.deleteById(id);
    }
    
    
}
