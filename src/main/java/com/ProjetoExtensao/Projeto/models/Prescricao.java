package com.ProjetoExtensao.Projeto.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescricoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicamento", nullable = false, length = 255)
    private String medicamento;

    @Column(name = "dosagem", length = 100)
    private String dosagem;

    @Column(name = "frequencia", length = 100)
    private String frequencia;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", nullable = false)
    private ProntuarioMedico prontuarioMedico;

    public Prescricao() {}

    public Prescricao(String medicamento, String dosagem, String frequencia, LocalDate dataInicio) {
        this.medicamento = medicamento;
        this.dosagem = dosagem;
        this.frequencia = frequencia;
        this.dataInicio = dataInicio;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public ProntuarioMedico getProntuarioMedico() {
        return prontuarioMedico;
    }

    public void setProntuarioMedico(ProntuarioMedico prontuarioMedico) {
        this.prontuarioMedico = prontuarioMedico;
    }
}