package com.app.clienteapp.controller;

import com.app.clienteapp.dto.ServicioDTO;
import com.app.clienteapp.service.ServicioService;
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
@RequestMapping("/servicios")
@CrossOrigin(origins = "http://localhost:3001")
public class ServicioController {
    
      @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<ServicioDTO> mostrarDirecciones() {
        return servicioService.findAll();
    }
  @GetMapping("/{id}")
    public ServicioDTO buscarServicioId(@PathVariable Long id){
        
        return servicioService.findById(id)        
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));
        
       
    }
    
    @PostMapping
    public ServicioDTO CrearDirecciones(@RequestBody ServicioDTO servicioDTO) {
        return servicioService.save(servicioDTO);
    }

    @DeleteMapping("/{id}")
    public void borrarContacto(@PathVariable Long id) {
        servicioService.deleteById(id);
    }
    
}
