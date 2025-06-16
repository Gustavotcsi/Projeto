package com.ProjetoExtensao.Projeto.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "internacoes")
public class Internacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "motivo_internacao", columnDefinition = "TEXT")
    private String motivoInternacao;

    @Column(name = "diagnostico_internacao", columnDefinition = "TEXT")
    private String diagnosticoInternacao;

    @Column(name = "hospital", length = 255)
    private String hospital;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", nullable = false)
    private ProntuarioMedico prontuarioMedico;

    public Internacao() {}

    public Internacao(LocalDate dataEntrada, LocalDate dataSaida,
                      String motivoInternacao, String diagnosticoInternacao) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.motivoInternacao = motivoInternacao;
        this.diagnosticoInternacao = diagnosticoInternacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getMotivoInternacao() {
        return motivoInternacao;
    }

    public void setMotivoInternacao(String motivoInternacao) {
        this.motivoInternacao = motivoInternacao;
    }

    public String getDiagnosticoInternacao() {
        return diagnosticoInternacao;
    }

    public void setDiagnosticoInternacao(String diagnosticoInternacao) {
        this.diagnosticoInternacao = diagnosticoInternacao;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public ProntuarioMedico getProntuarioMedico() {
        return prontuarioMedico;
    }

    public void setProntuarioMedico(ProntuarioMedico prontuarioMedico) {
        this.prontuarioMedico = prontuarioMedico;
    }
}
