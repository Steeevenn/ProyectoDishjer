package com.app.clienteapp.service;

import com.app.clienteapp.dto.ClienteDTO;
import com.app.clienteapp.model.Cliente;
import com.app.clienteapp.repository.ClienteRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.clienteapp.mapper.MapperInterface;
import jakarta.transaction.Transactional;
import java.util.Optional;
// Indica que es un servicio  o componeneten de la aplicacion  continene la logica de negocio
// Inyecta las dependencias de una clase llamada ClienteRepository por medio de la anotacion autowired
@Service
public class ClienteService {
    

    @Autowired
    private ClienteRepository clienteRepository;
    
    private final MapperInterface clienteMapper = MapperInterface.INSTANCE;
    
    //Metodo para mostrar todos los clientes 
    public List<ClienteDTO> findAll(){
              
       return clienteRepository.findAll().stream()
              .map(clienteMapper::clienteToClienteDTO)
              .collect(Collectors.toList()) ;
       
    }
    
    @Transactional
    // metodo para guardar un objeto de tipo ClienteDTO
    public ClienteDTO save(ClienteDTO clienteDTO){
      
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        
        
    return  clienteMapper.clienteToClienteDTO(savedCliente);
            
    }
    // Metodo para borrar datos 
    public void deleteById(Long id){
    clienteRepository.deleteById(id);
    }
    
    // Optional se usa aquí para manejar la posibilidad de que no se encuentre ningún cliente con el ID proporcionado
    //de manera segura, evitando así posibles excepciones de puntero nulo (NullPointerException).
    public  Optional <ClienteDTO> findById(Long id){
        
        Optional <Cliente> clienteOpcional = clienteRepository.findById(id);
        return clienteOpcional.map(clienteMapper::clienteToClienteDTO);
    
    } 
}
