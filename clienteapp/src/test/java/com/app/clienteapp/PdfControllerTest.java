package com.app.clienteapp;

import com.app.clienteapp.controller.PdfController;
import com.app.clienteapp.model.Cliente;
import com.app.clienteapp.model.PdfCliente;
import com.app.clienteapp.model.ServicioTecnicoJson;
import com.app.clienteapp.repository.ClienteRepository;
import com.app.clienteapp.repository.pdfClienteRepository;
import com.app.clienteapp.service.PdfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PdfControllerTest {

    @Autowired
    private PdfController pdfController;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private pdfClienteRepository pdfRepository;

    @Autowired
    private PdfService pdfService;

    @Test
    public void testGenerateAndDownloadPdf() {
        // Crear un cliente de prueba
        Cliente cliente = new Cliente();
        cliente.setNombreCliente("Test Cliente");
        clienteRepository.save(cliente);

        // Crear datos de servicio técnico de prueba
        ServicioTecnicoJson jsonData = new ServicioTecnicoJson();
        jsonData.setId(cliente.getId());
        jsonData.setNombreCliente(cliente.getNombreCliente());

        // Generar PDF
        pdfService.generatePdfFromJson(jsonData);

        // Verificar que el PDF se guardó en el repositorio
        Optional<PdfCliente> pdfOpt = pdfRepository.findByClienteId(cliente.getId());
        assertThat(pdfOpt).isPresent();
        assertThat(pdfOpt.get().getDataPdf()).isNotNull();

        // Probar descarga de PDF
        ResponseEntity<byte[]> response = pdfController.downloadPdf(cliente.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}

