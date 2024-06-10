package com.app.clienteapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

// Representa una entidad en la base de datos
@Entity
public class Cliente {
    // Identificador unico de esta entidad  

    @Id
    // Especifica como se genera el valor del id en este caso automaticamente la base de datos lo genera
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCliente;
    
    // Uno a muchos 
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contacto> contactos;

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

  
  
    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    
    
}
