package com.app.clienteapp.dto;

import java.time.LocalDate;

public class SolicitudServiceDTO {
   private Long id;

    private String tipoServicio;
    private String observaciones;
    private Long clienteServiceId;
    private LocalDate fechaServicio;
    
    public Long getClienteServiceId() {
        return clienteServiceId;
    }

    public void setClienteServiceId(Long clienteServiceId) {
        this.clienteServiceId = clienteServiceId;
    }

    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }


 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
}
