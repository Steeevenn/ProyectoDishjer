package com.app.clienteapp.dto;



public class ContactoDTO {
    private Long id;
    private String nombreContacto;
    private TelefonoDTO telefono;
    private Long clienteId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

  

  

    public TelefonoDTO getTelefono() {
        return telefono;
    }

    public void setTelefono(TelefonoDTO telefono) {
        this.telefono = telefono;
    }

}

    
    

