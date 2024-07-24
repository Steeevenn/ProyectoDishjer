package com.app.clienteapp.model;

import jakarta.persistence.*;

@Entity
public class PdfCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pdf", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] dataPdf;


    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @OneToOne()
    private Cliente cliente;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDataPdf() {
        return dataPdf;
    }

    public void setDataPdf(byte[] dataPdf) {
        this.dataPdf = dataPdf;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}