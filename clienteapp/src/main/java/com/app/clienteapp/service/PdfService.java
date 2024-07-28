// Importa las clases necesarias
package com.app.clienteapp.service;

import com.app.clienteapp.model.*;
import com.app.clienteapp.repository.ClienteRepository;
import com.app.clienteapp.repository.pdfClienteRepository;
import com.itextpdf.commons.actions.IEvent;
import com.itextpdf.commons.actions.IEventHandler;
import com.itextpdf.forms.form.element.TextArea;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.properties.*;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Phaser;

@Service
public class PdfService {

    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final pdfClienteRepository pdfClienteRepository;

    public PdfService(ClienteRepository clienteRepository, pdfClienteRepository pdfClienteRepository) throws IOException {
        this.clienteRepository = clienteRepository;
        this.pdfClienteRepository = pdfClienteRepository;
    }

    PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

    public ByteArrayInputStream generatePdfFromJson(ServicioTecnicoJson jsonData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);

            try (Document document = new Document(pdfDoc)) {
                // Estilos
                Style titleStyle = new Style().setFontSize(20).setFontColor(ColorConstants.BLACK).setBold();
                Style nameCliente = new Style().setFontSize(17).setFontColor(ColorConstants.DARK_GRAY).setBold();
                Style subtitleStyle = new Style().setFontSize(14).setFontColor(ColorConstants.DARK_GRAY).setBold();
                Style normalStyle = new Style().setFontSize(11).setFontColor(ColorConstants.BLACK);
                Style tableHeaderStyle = new Style().setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold();
                Style fieldStyle = new Style().setFontSize(10).setFontColor(ColorConstants.DARK_GRAY).setFontSize(13)/*.setFont(font)*/.setFontColor(ColorConstants.BLACK).setBold();
                // Crear un párrafo con campos alineados
                Paragraph title = new Paragraph().addTabStops(new TabStop(360, TabAlignment.LEFT)) // Ajustar las posiciones de tabulación según sea necesario
                        .add(new Text("REPORTE DE SERVICIO").addStyle(titleStyle)).add(new Tab()).add(new Text("Orden de Servicio No. " + jsonData.getId()).addStyle(fieldStyle));

                document.add(title);
                Paragraph NIT = new Paragraph().addTabStops(new TabStop(412, TabAlignment.LEFT)) // Ajustar las posiciones de tabulación según sea necesario
                        .add(new Tab()).add(new Text("NIT: 19.365.931-1").addStyle(fieldStyle));

                document.add(NIT);

                // Agregar un subrayado
                LineSeparator lineSeparator = new LineSeparator(new SolidLine());
                lineSeparator.setWidth(UnitValue.createPercentValue(100)); // Asegura que la línea ocupe el 100% del ancho
                lineSeparator.setMarginTop(10); // Espaciado superior
                lineSeparator.setMarginBottom(10); // Espaciado inferior
                document.add(lineSeparator);


                // Agrega información del cliente
                document.add(new Paragraph("Cliente: " + jsonData.getNombreCliente()).addStyle(nameCliente));
                document.add(new Paragraph("\n"));// añade un salto de línea;

                // Contactos
                Table contactoTable = new Table(UnitValue.createPercentArray(new float[]{4, 4})).addStyle(subtitleStyle).useAllAvailableWidth();
                contactoTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.3f);
                contactoTable.addHeaderCell(new Cell().add(new Paragraph("Contacto")).addStyle(tableHeaderStyle));
                contactoTable.addHeaderCell(new Cell().add(new Paragraph("Telefono")).addStyle(tableHeaderStyle));


                for (ServicioTecnicoJson.Contacto contacto : jsonData.getContactos()) {
                    contactoTable.addCell(new Cell().add(new Paragraph(contacto.getNombreContacto())).addStyle(normalStyle));
                    contactoTable.addCell(new Cell().add(new Paragraph(contacto.getTelefono().getNumero())).addStyle(normalStyle));
                }
                document.add(contactoTable);

                //Salto de linea
                document.add(new Paragraph("\n"));

                // Datos de direcciones

                Table direccionTable = new Table(UnitValue.createPercentArray(new float[]{4, 4, 4})).addStyle(subtitleStyle).useAllAvailableWidth();
                direccionTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.3f);
                direccionTable.addHeaderCell(new Cell().add(new Paragraph("Direccion")).addStyle(tableHeaderStyle));
                direccionTable.addHeaderCell(new Cell().add(new Paragraph("Barrio")).addStyle(tableHeaderStyle));
                direccionTable.addHeaderCell(new Cell().add(new Paragraph("Ciudad")).addStyle(tableHeaderStyle));

                for (ServicioTecnicoJson.Direccion dir : jsonData.getDirecciones()) {
                    direccionTable.addCell(new Cell().add(new Paragraph(dir.getDireccion())).addStyle(normalStyle));
                    direccionTable.addCell(new Cell().add(new Paragraph(dir.getBarrio())).addStyle(normalStyle));
                    direccionTable.addCell(new Cell().add(new Paragraph(dir.getCiudad())).addStyle(normalStyle));
                }
                document.add(direccionTable);

                //Salto de linea
               // document.add(new Paragraph("\n"));

                for (ServicioTecnicoJson.Equipo equipo : jsonData.getEquipos()) {
                    // Repuestos
                    document.add(new Paragraph("Repuestos:").addStyle(subtitleStyle).setMarginTop(10));
                    Table repuestoTable = new Table(UnitValue.createPercentArray(new float[]{4, 4, 4})).useAllAvailableWidth();
                    repuestoTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.3f);
                    repuestoTable.addHeaderCell(new Cell().add(new Paragraph("Cantidad")).addStyle(tableHeaderStyle));
                    repuestoTable.addHeaderCell(new Cell().add(new Paragraph("Descripcion")).addStyle(tableHeaderStyle));
                    repuestoTable.addHeaderCell(new Cell().add(new Paragraph("Tipo")).addStyle(tableHeaderStyle));

                    for (ServicioTecnicoJson.Repuesto repuesto : equipo.getRepuestos()) {
                        repuestoTable.addCell(new Cell().add(new Paragraph(String.valueOf(repuesto.getCantidad()))).addStyle(normalStyle));
                        repuestoTable.addCell(new Cell().add(new Paragraph(repuesto.getDescripcion())).addStyle(normalStyle));
                        repuestoTable.addCell(new Cell().add(new Paragraph(repuesto.getTipo())).addStyle(normalStyle));
                    }
                    document.add(repuestoTable);
                    //Salto de linea
                 //   document.add(new Paragraph("\n"));

                    // Datos servicio Servicio
                    document.add(new Paragraph("Informacion Servicio:").addStyle(subtitleStyle).setMarginTop(10));
                    Table servicioTable = new Table(UnitValue.createPercentArray(new float[]{4, 4, 4, 4, 4})).useAllAvailableWidth();
                    servicioTable.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.3f);
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Fecha")).addStyle(tableHeaderStyle));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Hora Inicio")).addStyle(tableHeaderStyle));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Hora Fin")).addStyle(tableHeaderStyle));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Tipo Servicio")).addStyle(tableHeaderStyle));
                    servicioTable.addHeaderCell(new Cell().add(new Paragraph("Tipo Contrato")).addStyle(tableHeaderStyle));

                    for (ServicioTecnicoJson.Servicio servicio : equipo.getServicios()) {
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getFecha().toString())).addStyle(normalStyle));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getHoraInicio().toString())).addStyle(normalStyle));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getHoraFin().toString())).addStyle(normalStyle));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getTipoServicio())).addStyle(normalStyle));
                        servicioTable.addCell(new Cell().add(new Paragraph(servicio.getTipoContrato())).addStyle(normalStyle));
                    }
                    document.add(servicioTable);

                    document.add(new Paragraph("\n"));
                    // Datos de equipos sin tabla
                    document.add(new Paragraph("Datos de Equipo:").addStyle(subtitleStyle));
                    for (ServicioTecnicoJson.Equipo equipo01 : jsonData.getEquipos()) {
                        document.add(new Paragraph("Serial Equipo: " + equipo01.getSerialEquipo()));
                        document.add(new Paragraph("Contador Color: " + equipo01.getContadorColor()));
                        document.add(new Paragraph("Contador Blanco y Negro: " + equipo01.getContadorBlancoNegro()));
                        //Salto de linea
                        document.add(new Paragraph("\n"));
                        document.add(new Paragraph("Observaciones:").addStyle(subtitleStyle).setMarginTop(10));
                        for (ServicioTecnicoJson.Servicio servicio : equipo.getServicios()) {
                            Table observacionesTable = new Table(UnitValue.createPercentArray(new float[]{1})).useAllAvailableWidth();
                            observacionesTable.addCell(new Cell().add(new Paragraph(servicio.getObservaciones())).addStyle(normalStyle));
                            document.add(observacionesTable);
                        }
                        // Añadir texto al final de cada página recorre todo el documento y genera el cambas al final
                        int numberOfPages = pdfDoc.getNumberOfPages();
                        for (int i = 1; i <= numberOfPages; i++) {
                            PdfPage page = pdfDoc.getPage(i);
                            Rectangle pageSize = page.getPageSize();
                            PdfCanvas pdfCanvas = new PdfCanvas(page);
                            Canvas canvas = new Canvas(pdfCanvas,pageSize,true );
                            canvas.setFontSize(10);
                            canvas.showTextAligned("© 2024 DISHJER. Todos los derechos reservados.",
                                    pageSize.getWidth() / 2, 20, TextAlignment.CENTER);
                            canvas.close();
                        }

                    }

                    addWatermark(pdfDoc);
                    document.close();


                    byte[] pdfBytes = out.toByteArray();


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
        } finally {
            System.out.println("Datos correctos");
        }

    }

    // metod auxiliar para manejar el evento de agregar la marca de agua
  public void addWatermark(PdfDocument pdfDoc) {
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numberOfPages = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            PdfPage pdfPage = pdfDoc.getPage(i);
            Rectangle pageSize = pdfPage.getPageSize();

            PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamAfter(), pdfPage.getResources(), pdfDoc);
            pdfCanvas.saveState();
            pdfCanvas.setFillColor(ColorConstants.LIGHT_GRAY);
            //Opacidad en el watermak
            pdfCanvas.setExtGState(new PdfExtGState().setFillOpacity(0.2f));

            Canvas canvas = new Canvas(pdfPage,pageSize);
            canvas.setFont(font);
            canvas.setFontSize(150);
            canvas.showTextAligned(
                    new Paragraph("DISJhER"),
                    pageSize.getWidth() / 2,
                    pageSize.getHeight() / 2,
                    pdfPage.getRotation(),
                    com.itextpdf.layout.properties.TextAlignment.CENTER,
                    com.itextpdf.layout.properties.VerticalAlignment.MIDDLE,
                    45
            );
            pdfCanvas.restoreState();
        }

}
}

