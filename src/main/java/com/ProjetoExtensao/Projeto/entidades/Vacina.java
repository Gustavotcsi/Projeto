package com.ProjetoExtensao.Projeto.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacinas")
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_vacina", nullable = false, length = 100)
    private String nomeVacina;

    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;

    @Column(name = "lote", length = 50)
    private String lote;

    @Column(name = "via_aplicacao", length = 50)
    private String viaAplicacao;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", nullable = false)
    private ProntuarioMedico prontuarioMedico;

    public Vacina() {}

    public Vacina(String nomeVacina, LocalDate dataAplicacao) {
        this.nomeVacina = nomeVacina;
        this.dataAplicacao = dataAplicacao;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getViaAplicacao() {
        return viaAplicacao;
    }

    public void setViaAplicacao(String viaAplicacao) {
        this.viaAplicacao = viaAplicacao;
    }

    public ProntuarioMedico getProntuarioMedico() {
        return prontuarioMedico;
    }

    public void setProntuarioMedico(ProntuarioMedico prontuarioMedico) {
        this.prontuarioMedico = prontuarioMedico;
    }
}
