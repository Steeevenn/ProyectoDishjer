package com.app.clienteapp.repository;

import com.app.clienteapp.model.PdfCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface pdfClienteRepository extends JpaRepository<PdfCliente, Long> {
    Optional<PdfCliente> findByClienteId(Long clienteId);
}
