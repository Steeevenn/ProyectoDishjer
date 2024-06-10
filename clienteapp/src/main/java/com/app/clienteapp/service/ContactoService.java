package com.app.clienteapp.service;

import com.app.clienteapp.model.Contacto;
import com.app.clienteapp.repository.ContactoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {
// Inyecto la dependencia de contacto repository

    @Autowired
    private ContactoRepository contactoRepository;

    public List<Contacto> findAll() {

        return contactoRepository.findAll();
    }

    // metodo para guardar un objeto de tipo cliente
    public Contacto save(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public void deleteById(Long id) {
        contactoRepository.deleteById(id);
    }

}
