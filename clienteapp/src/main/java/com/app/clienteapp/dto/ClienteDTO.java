package com.app.clienteapp.dto;
// Data Object Transfer para tansferir objetos de una representacion 

import jakarta.persistence.Lob;

import java.util.List;

public class ClienteDTO {

    private long id;
    private String nombreCliente;

    @Lob
    private byte[] pdf;

    private List<DireccionDTO> direcciones;

    public List<DireccionDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionDTO> direcciones) {
        this.direcciones = direcciones;
    }
    private List<EquipoDTO> equipos;
    private List<ContactoDTO> contactos;


    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }

    public List<ContactoDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactoDTO> contactos) {
        this.contactos = contactos;
    }
    
    

}
