package com.app.clienteapp.dto;

import java.util.List;

public class EquipoDTO {

    private Long id;
    private String serialEquipo;
    private Long contadorColor;
    private Long contadorBlancoNegro;
    private List<ServicioDTO> servicios;
    private List<RepuestoDTO> repuestos;

    private Long clienteId;

    
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }

    public List<RepuestoDTO> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<RepuestoDTO> repuestos) {
        this.repuestos = repuestos;
    }

    public String getSerialEquipo() {
        return serialEquipo;
    }

    public void setSerialEquipo(String serial) {
        this.serialEquipo = serial;
    }

    public Long getContadorColor() {
        return contadorColor;
    }

    public void setContadorColor(Long contadorColor) {
        this.contadorColor = contadorColor;
    }

    public Long getContadorBlancoNegro() {
        return contadorBlancoNegro;
    }

    public void setContadorBlancoNegro(Long contadorBlancoNegro) {
        this.contadorBlancoNegro = contadorBlancoNegro;
    }

  
}
