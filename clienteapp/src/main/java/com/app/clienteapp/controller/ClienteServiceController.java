package com.app.clienteapp.controller;

import com.app.clienteapp.dto.ClienteDTO;
import com.app.clienteapp.dto.ClienteServiceDTO;
import com.app.clienteapp.service.ClienteSolicitudService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/clientesService")
public class ClienteServiceController {

    @Autowired
    private ClienteSolicitudService clienteSolicitudService;
    
     @PostMapping()
    public ClienteServiceDTO crearCliente(@RequestBody ClienteServiceDTO clienteServiceDTO) {
     System.out.println("Datos recibidos del cliente:");
    System.out.println(clienteServiceDTO);

        return clienteSolicitudService.save(clienteServiceDTO);
    }
    
    @GetMapping()
    public List <ClienteServiceDTO> mostrarClientes(){

        return clienteSolicitudService.findall();
    }

    
}
