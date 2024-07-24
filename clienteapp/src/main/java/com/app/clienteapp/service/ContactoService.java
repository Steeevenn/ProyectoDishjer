package com.app.clienteapp.service;

import com.app.clienteapp.dto.ContactoDTO;
import com.app.clienteapp.mapper.MapperInterface;
import com.app.clienteapp.model.Contacto;
import com.app.clienteapp.repository.ContactoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {
// Inyecto la dependencia de contacto repository

    @Autowired
    private ContactoRepository contactoRepository;
    private final MapperInterface contactoMapper = MapperInterface.INSTANCE;

    
    public List<ContactoDTO> findAll(){
        return contactoRepository.findAll().stream()
                .map(contactoMapper::contactoToContactoDTO)
                .collect(Collectors.toList());
    }

    // metodo para guardar un objeto de tipo cliente
    @Transactional
    public ContactoDTO save(ContactoDTO contactoDTO) {
       Contacto contacto = contactoMapper.contactoDTOToContacto(contactoDTO);
       Contacto contactoSave = contactoRepository.save(contacto);
       
       return contactoMapper.contactoToContactoDTO(contactoSave);
        
    }

    public void deleteById(Long id) {
        contactoRepository.deleteById(id);
    }

}
