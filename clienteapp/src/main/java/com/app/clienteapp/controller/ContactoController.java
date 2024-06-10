package com.app.clienteapp.controller;

import com.app.clienteapp.model.Contacto;
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
    public List<Contacto> mostrarContacto(){
    return contactoService.findAll();
    }
    
    @PostMapping()
    public Contacto crearContacto(@RequestBody Contacto contacto){
    return contactoService.save(contacto);
    }
    
    @DeleteMapping("{id}")
    public void borrarContacto(@PathVariable Long id){
    contactoService.deleteById(id);
    }
}
