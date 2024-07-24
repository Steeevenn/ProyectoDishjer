package com.app.clienteapp.controller;

import com.app.clienteapp.dto.*;
import com.app.clienteapp.service.ContactoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contactos")
@CrossOrigin(origins = "http://localhost:3001")
public class ContactoController {
    
    @Autowired
    private ContactoService contactoService;
    
    @GetMapping()
    public List<ContactoDTO> mostrarContacto(){
        return contactoService.findAll();
   
    }
    
    @PostMapping()
    public ContactoDTO crearContacto(@RequestBody ContactoDTO contactoDTO){
    return contactoService.save(contactoDTO);
    }
    
    @DeleteMapping("{id}")
    public void borrarContacto(@PathVariable Long id){
    contactoService.deleteById(id);
    }
}
