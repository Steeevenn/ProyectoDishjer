package com.app.clienteapp.service;

import com.app.clienteapp.model.Cliente;
import com.app.clienteapp.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    

    @Autowired
    private ClienteRepository clienteRepository;
    
    //Metodo para mostrar todos los clientes 
    public List<Cliente> findAll(){
    return clienteRepository.findAll();
    }
    
    // metodo para guardar un objeto de tipo cliente
    public Cliente save(Cliente cliente){
    return clienteRepository.save(cliente);
    }
    
    public void  deleteById(Long id){
    clienteRepository.deleteById(id);
    }
    
}
