package com.app.clienteapp.service;

import com.app.clienteapp.dto.EquipoDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.Equipo;
import com.app.clienteapp.repository.EquipoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {
    
       @Autowired
    private EquipoRepository equipoRepository;
       
           private final MapperInterface clienteMapper = MapperInterface.INSTANCE;


    public List<EquipoDTO> findAll() {
        return equipoRepository.findAll().stream()
                .map(clienteMapper::equipoToEquipoDTO)
                .collect(Collectors.toList());
       
    }

    // metodo para guardar un objeto de tipo cliente
    @Transactional
    public EquipoDTO save(EquipoDTO equipoDTO) {
       Equipo equipo = clienteMapper.equipoDTOToEquipo(equipoDTO);
       Equipo equiposaved = equipoRepository.save(equipo);
       
        return clienteMapper.equipoToEquipoDTO(equiposaved);
    }

    public void deleteById(Long id) {
        equipoRepository.deleteById(id);
    }

}
