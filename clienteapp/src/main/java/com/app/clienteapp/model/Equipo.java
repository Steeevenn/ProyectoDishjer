package com.app.clienteapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialEquipo;
    
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Servicio> servicios;
    
    private int contadorColor;
    private int contadorBlancoNegro;
    
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Repuesto> repuestos; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialEquipo() {
        return serialEquipo;
    }

    public void setSerialEquipo(String serial) {
        this.serialEquipo = serial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public int getContadorColor() {
        return contadorColor;
    }

    public void setContadorColor(int contadorColor) {
        this.contadorColor = contadorColor;
    }

    public int getContadorBlancoNegro() {
        return contadorBlancoNegro;
    }

    public void setContadorBlancoNegro(int contadorBlancoNegro) {
        this.contadorBlancoNegro = contadorBlancoNegro;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }
    

    
}
