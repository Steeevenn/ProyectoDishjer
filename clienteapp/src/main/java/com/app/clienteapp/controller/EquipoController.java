package com.app.clienteapp.controller;

import com.app.clienteapp.dto.EquipoDTO;
import com.app.clienteapp.service.EquipoService;
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
@RequestMapping("/equipos")
@CrossOrigin(origins = "http://localhost:3001")
public class EquipoController {
    
    @Autowired
    private EquipoService equipoService;

    
    @GetMapping
    public List<EquipoDTO> mostrarDirecciones() {
        return equipoService.findAll();
                
    }

    @PostMapping
    public EquipoDTO CrearDirecciones(@RequestBody EquipoDTO equipoDTO) {
        return equipoService.save(equipoDTO);
    }

    @DeleteMapping("/{id}")
    public void borrarContacto(@PathVariable Long id) {
        equipoService.deleteById(id);
    }
    
}
