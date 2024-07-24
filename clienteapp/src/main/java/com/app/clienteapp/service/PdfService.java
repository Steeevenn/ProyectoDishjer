package com.app.clienteapp.service;
// PdfService.java
import com.app.clienteapp.model.*;
import com.app.clienteapp.repository.ClienteRepository;
import com.app.clienteapp.repository.pdfClienteRepository;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class PdfService {

    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final com.app.clienteapp.repository.pdfClienteRepository pdfClienteRepository;

    public PdfService(ClienteRepository clienteRepository, pdfClienteRepository pdfClienteRepository) {
        this.clienteRepository = clienteRepository;
        this.pdfClienteRepository = pdfClienteRepository;
    }

    public ByteArrayInputStream generatePdfFromJson(ServicioTecnicoJson jsonData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            // Agrega título
            try (Document document = new Document(pdfDoc)) {

                // estilo para texto
                Style titleStyle = new Style()
                        .setFontSize(18)
                        .setFontColor(ColorConstants.DARK_GRAY)
                        .setBold();


                // Agrega título y estilo
                document.add(new Paragraph("Servicio Tecnico Dishjer").addStyle(titleStyle));

                // separador de linea
                document.add(new LineSeparator(new SolidLine()));



                // Agregar marca de agua
           //     Watermark watermark = new Watermark(new Image(ImageDataFactory.create("ruta/a/marca_de_agua.png")));
            //    document.add(watermark);


                // Agrega información del cliente
                document.add(new Paragraph("Nombre Cliente: " + jsonData.getNombreCliente()));
                document.add(new Paragraph("ID Cliente: " + jsonData.getId()));


                // Contactos
                document.add(new Paragraph("Datos de Contacto"));
                Table contactoTable = new Table(UnitValue.createPercentArray(new float[]{4, 4})).useAllAvailableWidth();
                // estilo a la tabla
                contactoTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.5f);

                contactoTable.addHeaderCell(new Cell().add(new Paragraph("Nombre Contacto")));
                contactoTable.addHeaderCell(new Cell().add(new Paragraph("Telefono")));
                for (ServicioTecnicoJson.Contacto contacto : jsonData.getContactos()) {
                    contactoTable.addCell(new Cell().add(new Paragraph(contacto.getNombreContacto())));
                    contactoTable.addCell(new Cell().add(new Paragraph(contacto.getTelefono().getNumero())));
                }
                document.add(contactoTable);

                // Direcciones
                document.add(new Paragraph("Datos de Cliente"));
                Table direccionTable = new Table(UnitValue.createPercentArray(new float[]{4, 4, 4})).useAllAvailableWidth();
                // estilo a la tabla
                direccionTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.5f);

                direccionTable.addHeaderCell(new Cell().add(new Paragraph("Direccion")));
                direccionTable.addHeaderCell(new Cell().add(new Paragraph("Barrio")));
                direccionTable.addHeaderCell(new Cell().add(new Paragraph("Ciudad")));
                for (ServicioTecnicoJson.Direccion dir : jsonData.getDirecciones()) {
                    direccionTable.addCell(new Cell().add(new Paragraph(dir.getDireccion())));
                    direccionTable.addCell(new Cell().add(new Paragraph(dir.getBarrio())));
                    direccionTable.addCell(new Cell().add(new Paragraph(dir.getCiudad())));
                }
                document.add(direccionTable);
                
                // Equipos
                document.add(new Paragraph("Datos de Equipo:"));
                for (ServicioTecnicoJson.Equipo equipo : jsonData.getEquipos()) {
                    document.add(new Paragraph("Serial Equipo: " + equipo.getSerialEquipo()));
                    document.add(new Paragraph("Contador Color: " + equipo.getContadorColor()));
                    document.add(new Paragraph("Contador Blanco y Negro: " + equipo.getContadorBlancoNegro()));


                    // respuestos
                    document.add(new Paragraph("Datos de Repuestos:"));
                    Table repuestoTable = new Table(UnitValue.createPercentArray(new float[]{4, 4, 4})).useAllAvailableWidth();
                    // estilo a la tabla
                    repuestoTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.5f);

                    repuestoTable.addHeaderCell(new Cell().add(new Paragraph("Cantidad")));
                    repuestoTable.addHeaderCell(new Cell().add(new Paragraph("Descripcion")));
                    repuestoTable.addHeaderCell(new Cell().add(new Paragraph("Tipo")));
                    for (ServicioTecnicoJson.Repuesto repuesto : equipo.getRepuestos()) {
                        repuestoTable.addCell(new Cell().add(new Paragraph(String.valueOf(repuesto.getCantidad()))));
                        repuestoTable.addCell(new Cell().add(new Paragraph(repuesto.getDescripcion())));
                        repuestoTable.addCell(new Cell().add(new Paragraph(repuesto.getTipo())));
                    }
                    document.add(repuestoTable);

                    // Servicio
                    document.add(new Paragraph("Datos de Servicio"));
                    Table servicioTable = new Table(UnitValue.createPercentArray(new float[]{4, 4, 4, 4, 4})).useAllAvailableWidth();
                    servicioTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.5f);

                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Fecha")));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Hora Inicio")));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Hora Fin")));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Tipo Servicio")));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Tipo Contrato")));
                    for (ServicioTecnicoJson.Servicio servicio : equipo.getServicios()) {
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getFecha().toString())));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getHoraInicio().toString())));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getHoraFin().toString())));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getTipoServicio())));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getTipoContrato())));
                    }
                    document.add(servicioTable);

                    document.add(new Paragraph(""));
                    // Tabla de observaciones
                    document.add(new Paragraph("Observaciones:"));
                    for (ServicioTecnicoJson.Servicio servicio : equipo.getServicios()) {
                        Table observacionesTable = new Table(UnitValue.createPercentArray(new float[]{1})).useAllAvailableWidth();
                        observacionesTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.5f);
                        observacionesTable.addCell(new Cell().add(new Paragraph(servicio.getObservaciones())));
                        document.add(observacionesTable);
                    }



                }


                document.close();

                byte[] pdfBytes = out.toByteArray();

                // Guardar el PDF en la entidad Cliente codigo funcionando
              /*  Cliente cliente = new Cliente();
                cliente.setNombreCliente(jsonData.getNombreCliente());
                cliente.setPdf(pdfBytes);
                clienteRepository.save(cliente);

                return new ByteArrayInputStream(pdfBytes);
               */
                Optional<Cliente> clienteOpt = clienteRepository.findById(jsonData.getId());
                if (clienteOpt.isPresent()) {
                    Cliente cliente = clienteOpt.get();
                    PdfCliente pdfCliente = new PdfCliente();
                    pdfCliente.setDataPdf(pdfBytes);
                    pdfCliente.setCliente(cliente);
                    pdfClienteRepository.save(pdfCliente);
                }


                return new ByteArrayInputStream(pdfBytes);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
