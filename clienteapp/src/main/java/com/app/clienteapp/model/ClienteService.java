package com.app.clienteapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity 
public class ClienteService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreCliente;
    private String telefono;
    private String direccion;



    @OneToMany(mappedBy = "clienteService", cascade = CascadeType.ALL)
    private List<SolicitudServicioTecnico> solicitudes;



    public Long getId() {
        return id;
    }

    public List<SolicitudServicioTecnico> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudServicioTecnico> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


     
    
    
}
