package com.ProjetoExtensao.Projeto.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "eventos_sentinela")
public class EventoSentinela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "descricao_evento", nullable = false, columnDefinition = "TEXT")
    private String descricaoEvento;

    @Column(name = "data_ocorrencia", nullable = false)
    private LocalDate dataOcorrencia;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    public EventoSentinela() {}

    public EventoSentinela(Paciente paciente, String descricaoEvento, LocalDate dataOcorrencia) {
        this.paciente = paciente;
        this.descricaoEvento = descricaoEvento;
        this.dataOcorrencia = dataOcorrencia;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public LocalDate getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(LocalDate dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
