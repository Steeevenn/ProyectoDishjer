package com.app.clienteapp.controller;

import com.app.clienteapp.model.Contacto;
import com.app.clienteapp.model.Direccion;
import com.app.clienteapp.service.ContactoService;
import com.app.clienteapp.service.DireccionService;
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

@RestController
@RequestMapping("/direcciones")
@CrossOrigin(origins = "http://localhost:3001")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public List<Direccion> mostrarDirecciones() {
        return direccionService.findAll();
    }

    @PostMapping
    public Direccion CrearDirecciones(@RequestBody Direccion direccion) {
        return direccionService.save(direccion);
    }

    @DeleteMapping("/{id}")
    public void borrarContacto(@PathVariable Long id) {
        direccionService.deleteById(id);
    }
}
