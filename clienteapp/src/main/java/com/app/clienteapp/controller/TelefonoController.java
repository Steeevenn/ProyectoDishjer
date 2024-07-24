package com.app.clienteapp.controller;

import com.app.clienteapp.dto.TelefonoDTO;
import com.app.clienteapp.model.Servicio;
import com.app.clienteapp.model.Telefono;
import com.app.clienteapp.service.ServicioService;
import com.app.clienteapp.service.TelefonoService;
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
@RequestMapping("/telefonos")
@CrossOrigin(origins = "http://localhost:3001")
public class TelefonoController {

    @Autowired
    private TelefonoService telefonoService;

    @GetMapping
    public List<TelefonoDTO> mostrarDirecciones() {
        return telefonoService.findAll();
    }

    @PostMapping
    public TelefonoDTO CrearDirecciones(@RequestBody TelefonoDTO telefonoDTO) {
        return telefonoService.save(telefonoDTO);
    }

    @DeleteMapping("/{id}")
    public void borrarContacto(@PathVariable Long id) {
        telefonoService.deleteById(id);
    }

}
