package com.ProjetoExtensao.Projeto.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exames")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_exame", nullable = false, length = 255)
    private String nomeExame;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @Column(name = "data_resultado")
    private LocalDate dataResultado;

    @Column(name = "resultado", columnDefinition = "TEXT")
    private String resultado;

    @Column(name = "laboratorio", length = 100)
    private String laboratorio;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", nullable = false)
    private ProntuarioMedico prontuarioMedico;

    public Exame() {}

    public Exame(String nomeExame, LocalDate dataSolicitacao) {
        this.nomeExame = nomeExame;
        this.dataSolicitacao = dataSolicitacao;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(LocalDate dataResultado) {
        this.dataResultado = dataResultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public ProntuarioMedico getProntuarioMedico() {
        return prontuarioMedico;
    }

    public void setProntuarioMedico(ProntuarioMedico prontuarioMedico) {
        this.prontuarioMedico = prontuarioMedico;
    }

    public void vincularResultado(LocalDate dataResultado, String resultado) {
        this.dataResultado = dataResultado;
        this.resultado = resultado;
        System.out.println("Resultado do exame '" + nomeExame + "' vinculado.");
    }
}