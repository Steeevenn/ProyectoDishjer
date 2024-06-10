package com.app.clienteapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.clienteapp.model.Cliente;
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
    public List<Cliente> mostrarClientes() {
        return clienteService.findAll();
    }

    @PostMapping()
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void borrarCliete(@PathVariable Long id) {
        clienteService.deleteById(id);
    }

}
