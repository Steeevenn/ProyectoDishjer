package com.app.clienteapp.dto;

import java.util.List;


public class ClienteServiceDTO  {

    
    private Long id;
    private String nombreCliente;
    private String telefono;
    private String direccion;
    private byte[] servicioPdf;

    public List<SolicitudServiceDTO> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudServiceDTO> solicitudes) {
        this.solicitudes = solicitudes;
    }

    
    private List<SolicitudServiceDTO> solicitudes;

    public byte[] getServicioPdf() {
        return servicioPdf;
    }

    public void setServicioPdf(byte[] servicioPdf) {
        this.servicioPdf = servicioPdf;
    }

    public Long getId() {
        return id;
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
