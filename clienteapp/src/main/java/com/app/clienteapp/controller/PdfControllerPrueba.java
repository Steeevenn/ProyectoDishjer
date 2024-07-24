package com.app.clienteapp.controller;

import com.app.clienteapp.model.ServicioTecnicoJson;
import com.app.clienteapp.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generatedpdf")
@CrossOrigin(origins = "http://localhost:3001")
public class PdfControllerPrueba{

    private final PdfService pdfService;


    @Autowired
    public PdfControllerPrueba(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus savePdfToDatabase(@RequestBody ServicioTecnicoJson jsonData) {
        pdfService.generatePdfFromJson(jsonData);
        return HttpStatus.OK;
    }


}
