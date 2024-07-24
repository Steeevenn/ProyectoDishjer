package com.app.clienteapp.controller;


import com.app.clienteapp.dto.ClienteDTO;
import com.app.clienteapp.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public List<ClienteDTO> mostrarClientes() {
    List<ClienteDTO> clientes = clienteService.findAll();
    System.out.println("Clientes encontrados: " + clientes.size());
    return clientes;
    }
    
    @GetMapping("/{id}")
    public ClienteDTO buscarClienteId(@PathVariable Long id){
        
        return clienteService.findById(id)        
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
       
    }
    
    
  
    @PostMapping()
    public ClienteDTO crearCliente(@RequestBody ClienteDTO clienteDto) {
     System.out.println("Datos recibidos del cliente:");
    System.out.println(clienteDto);

        return clienteService.save(clienteDto);
    }

    @DeleteMapping("/{id}")
    public void borrarCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
    }

}
