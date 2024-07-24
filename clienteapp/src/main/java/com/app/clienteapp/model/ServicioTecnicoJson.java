package com.app.clienteapp.model;
        
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ServicioTecnicoJson {
    private Long id;
    private String nombreCliente;
    private List<Direccion> direcciones;
    private List<Equipo> equipos;
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

    // Getters y setters

    public static class Direccion {
        private Long id;
        private String direccion;
        private String barrio;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getBarrio() {
            return barrio;
        }

        public void setBarrio(String barrio) {
            this.barrio = barrio;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }
        private String ciudad;
        private Long clienteId;

        // Getters y setters
    }

    public static class Equipo {
        private Long id;
        private String serialEquipo;
        private Long contadorColor;
        private Long contadorBlancoNegro;
        private List<Servicio> servicios;
        private List<Repuesto> repuestos;
        private Long clienteId;
        
          

        // Getters y setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSerialEquipo() {
            return serialEquipo;
        }

        public void setSerialEquipo(String serialEquipo) {
            this.serialEquipo = serialEquipo;
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

        public List<Servicio> getServicios() {
            return servicios;
        }

        public void setServicios(List<Servicio> servicios) {
            this.servicios = servicios;
        }

        public List<Repuesto> getRepuestos() {
            return repuestos;
        }

        public void setRepuestos(List<Repuesto> repuestos) {
            this.repuestos = repuestos;
        }

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }
    }

    public static class Servicio {
        private Long id;
        private LocalDate fecha;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private String tipoServicio;
        private String tipoContrato;
        private String observaciones;
        private String firmaTecnico;
        private String firmaCliente;
        private Long equipoId;

        
        // Getters y setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public void setFecha(LocalDate fecha) {
            this.fecha = fecha;
        }

        public LocalTime getHoraInicio() {
            return horaInicio;
        }

        public void setHoraInicio(LocalTime horaInicio) {
            this.horaInicio = horaInicio;
        }

        public LocalTime getHoraFin() {
            return horaFin;
        }

        public void setHoraFin(LocalTime horaFin) {
            this.horaFin = horaFin;
        }

        public String getTipoServicio() {
            return tipoServicio;
        }

        public void setTipoServicio(String tipoServicio) {
            this.tipoServicio = tipoServicio;
        }

        public String getTipoContrato() {
            return tipoContrato;
        }

        public void setTipoContrato(String tipoContrato) {
            this.tipoContrato = tipoContrato;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public String getFirmaTecnico() {
            return firmaTecnico;
        }

        public void setFirmaTecnico(String firmaTecnico) {
            this.firmaTecnico = firmaTecnico;
        }

        public String getFirmaCliente() {
            return firmaCliente;
        }

        public void setFirmaCliente(String firmaCliente) {
            this.firmaCliente = firmaCliente;
        }

        public Long getEquipoId() {
            return equipoId;
        }

        public void setEquipoId(Long equipoId) {
            this.equipoId = equipoId;
        }
    }

    public static class Repuesto {
        private Long id;
        private int cantidad;
        private String descripcion;
        private String tipo;
        private Long equipoId;

        // Getters y setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Long getEquipoId() {
            return equipoId;
        }

        public void setEquipoId(Long equipoId) {
            this.equipoId = equipoId;
        }
    }

    public static class Contacto {
        private Long id;
        private String nombreContacto;
        private Telefono telefono;
        private Long clienteId;

        // Getters y setters

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

        public Telefono getTelefono() {
            return telefono;
        }

        public void setTelefono(Telefono telefono) {
            this.telefono = telefono;
        }

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }
    }

    public static class Telefono {
        private Long id;
        private String numero;
        private Long contactoId;

        // Getters y setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public Long getContactoId() {
            return contactoId;
        }

        public void setContactoId(Long contactoId) {
            this.contactoId = contactoId;
        }
    }
}