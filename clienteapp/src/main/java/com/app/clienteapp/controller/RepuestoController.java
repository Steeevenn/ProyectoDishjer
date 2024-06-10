package com.app.clienteapp.controller;

import com.app.clienteapp.model.Equipo;
import com.app.clienteapp.model.Repuesto;
import com.app.clienteapp.service.EquipoService;
import com.app.clienteapp.service.RepuestoService;
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
@RequestMapping("/repuestos")
@CrossOrigin(origins = "http://localhost:3001")
public class RepuestoController {
     
    @Autowired
    private RepuestoService repuestoService;

    @GetMapping
    public List<Repuesto> mostrarDirecciones() {
        return repuestoService.findAll();
    }

    @PostMapping
    public Repuesto CrearDirecciones(@RequestBody Repuesto Repuesto) {
        return repuestoService.save(Repuesto);
    }

    @DeleteMapping("/{id}")
    public void borrarContacto(@PathVariable Long id) {
        repuestoService.deleteById(id);
    }
    
}
