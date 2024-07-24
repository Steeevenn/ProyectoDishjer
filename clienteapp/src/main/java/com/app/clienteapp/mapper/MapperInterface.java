package com.app.clienteapp.mapper;

import com.app.clienteapp.dto.*;
import com.app.clienteapp.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperInterface {
    
    MapperInterface INSTANCE = Mappers.getMapper(MapperInterface.class);
    
    // Mapeo de entidades a DTOs
    ClienteDTO clienteToClienteDTO(Cliente cliente);
    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
    // Mapeo de ClienteService a ClienteServiceDTO
    ClienteServiceDTO clienteServiceToClienteServiceDTO(ClienteService clienteService);
    ClienteService clienteServiceDTOToClienteService(ClienteServiceDTO clienteServiceDTO);

    // Mapeo de SolicitudServicioTecnico a SolicitudServiceDTO
  @Mapping(source = "clienteServiceId", target = "clienteService.id")
    SolicitudServicioTecnico solicitudServiceDTOToSolicitudServicioTecnico(SolicitudServiceDTO solicitudServiceDTO);

   @Mapping(source = "clienteService.id", target = "clienteServiceId")
    SolicitudServiceDTO solicitudServicioTecnicoToSolicitudServiceDTO(SolicitudServicioTecnico solicitudServicioTecnico);
            
    @Mapping(source = "cliente.id", target = "clienteId")
    DireccionDTO direccionToDireccionDTO(Direccion direccion);
   
    @Mapping(source = "clienteId", target = "cliente.id")
    Direccion direccionDTOToDireccion(DireccionDTO direccionDTO);
    
    @Mapping(source = "cliente.id", target="clienteId")
    ContactoDTO contactoToContactoDTO(Contacto contacto);
    
    @Mapping(source = "clienteId" , target= "cliente.id")
    Contacto contactoDTOToContacto(ContactoDTO contactoDTO);
    
    @Mapping(source="cliente.id", target = "clienteId")
    EquipoDTO equipoToEquipoDTO(Equipo equipo);
    
    @Mapping(source="clienteId", target ="cliente.id")
    Equipo equipoDTOToEquipo(EquipoDTO equipoDTO);
    
    
    @Mapping(source="equipo.id", target="equipoId")
    RepuestoDTO repuestoToRepuestoDTO(Repuesto repuesto);
    
   @Mapping(source="equipoId",target="equipo.id")
    Repuesto repuestoDTOToRepuesto(RepuestoDTO repuestoDTO);
   
    
    @Mapping(source="equipo.id",target="equipoId")
    ServicioDTO servicioToServicioDTO(Servicio servicio);
    
    @Mapping(source="equipoId",target="equipo.id")
    Servicio servicioDTOToServicio(ServicioDTO servicioDTO);
   
    @Mapping(source="contacto.id",target="contactoId")    
    TelefonoDTO telefonoToTelefonoDTO(Telefono telefono);
    
    @Mapping(source="contactoId",target="contacto.id")
    Telefono telefonoDTOToTelefono(TelefonoDTO telefonoDTO);
    
    
}
