package com.app.clienteapp.controller;

import com.app.clienteapp.dto.SolicitudServiceDTO;
import com.app.clienteapp.service.SolicitudServicioTecService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/solicitudesServicio")
public class SolicitudServicioTecController {
    
    
@Autowired
private SolicitudServicioTecService  solicitudServicioTecService;

@PostMapping()
public SolicitudServiceDTO crearCliente(@RequestBody SolicitudServiceDTO solicitudServiceDTO) {
     System.out.println("Datos recibidos del cliente:");
    System.out.println(solicitudServiceDTO);

        return solicitudServicioTecService.save(solicitudServiceDTO);
    }

@GetMapping()
public List<SolicitudServiceDTO> solicitudes(){
    
    List<SolicitudServiceDTO> solicitudServiceDTO = solicitudServicioTecService.findAll();
    
    return solicitudServiceDTO;
}




    
    

}
