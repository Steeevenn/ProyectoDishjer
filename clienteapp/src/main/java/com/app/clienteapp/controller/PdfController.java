
package com.app.clienteapp.controller;
import com.app.clienteapp.model.Cliente;
import com.app.clienteapp.model.ServicioTecnicoJson;
import com.app.clienteapp.model.PdfCliente;
import com.app.clienteapp.repository.ClienteRepository;
import com.app.clienteapp.repository.pdfClienteRepository;
import com.app.clienteapp.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class PdfController {
    
    private final PdfService pdfService;

    @Autowired
    private pdfClienteRepository pdfRepository;

    @Autowired
    PdfController(PdfService pdfService){
        this.pdfService = pdfService;
    }

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping(value = "/generate-pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody ServicioTecnicoJson jsonData) {
        System.out.println("Generando PDF con los datos: " + jsonData); // Debugging line

        ByteArrayInputStream bis = pdfService.generatePdfFromJson(jsonData);
        System.out.println("PDF generado correctamente.");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cliente.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


    @GetMapping(value = "/download-pdf/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        System.out.println("ID recibido para descargar PDF: " + id); // Debugging line

        // Busca al cliente para verificar si existe
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isEmpty()) {
            System.out.println("Cliente no encontrado con ID: " + id); // Debugging line
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Cliente no encontrado
        }

        // Busca el PDF en el repositorio usando el ID del cliente
        Optional<PdfCliente> pdfOpt = pdfRepository.findByClienteId(id);
        if (pdfOpt.isPresent()) {
            PdfCliente pdfCliente = pdfOpt.get();
            byte[] pdfData = pdfCliente.getDataPdf();
            if (pdfData != null) {
                System.out.println("PDF encontrado para el cliente con ID: " + id); // Debugging line
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cliente_" + id + ".pdf\"")
                        .body(pdfData);
            } else {
                System.out.println("PDF no encontrado en el registro de PdfCliente"); // Debugging line
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // PDF no encontrado
            }
        } else {
            System.out.println("Registro de PdfCliente no encontrado para el cliente con ID: " + id); // Debugging line
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // PDF no encontrado
        }
    }


}








