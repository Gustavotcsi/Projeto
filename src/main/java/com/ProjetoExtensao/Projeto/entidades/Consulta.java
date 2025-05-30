package com.ProjetoExtensao.Projeto.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private ProfissionalSaude profissionalResponsavel;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "motivo_consulta", columnDefinition = "TEXT")
    private String motivoConsulta;

    @Column(name = "diagnostico_cid10", length = 20)
    private String diagnosticoCID10;

    @Column(name = "anotacoes_profissional", columnDefinition = "TEXT")
    private String anotacoesProfissional;

    @ElementCollection
    @CollectionTable(name = "consulta_encaminhamentos", joinColumns = @JoinColumn(name = "consulta_id"))
    @Column(name = "detalhe_encaminhamento")
    private List<String> encaminhamentos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "prontuario_id", nullable = false)
    private ProntuarioMedico prontuarioMedico;

    public Consulta() {}

    public Consulta(LocalDateTime dataHora, ProfissionalSaude profissionalResponsavel,
                    String tipo, String motivoConsulta, String diagnosticoCID10,
                    String anotacoesProfissional) {
        this.dataHora = dataHora;
        this.profissionalResponsavel = profissionalResponsavel;
        this.tipo = tipo;
        this.motivoConsulta = motivoConsulta;
        this.diagnosticoCID10 = diagnosticoCID10;
        this.anotacoesProfissional = anotacoesProfissional;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public ProfissionalSaude getProfissionalResponsavel() {
        return profissionalResponsavel;
    }

    public void setProfissionalResponsavel(ProfissionalSaude profissionalResponsavel) {
        this.profissionalResponsavel = profissionalResponsavel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnosticoCID10() {
        return diagnosticoCID10;
    }

    public void setDiagnosticoCID10(String diagnosticoCID10) {
        this.diagnosticoCID10 = diagnosticoCID10;
    }

    public String getAnotacoesProfissional() {
        return anotacoesProfissional;
    }

    public void setAnotacoesProfissional(String anotacoesProfissional) {
        this.anotacoesProfissional = anotacoesProfissional;
    }

    public List<String> getEncaminhamentos() {
        return encaminhamentos;
    }

    public void setEncaminhamentos(List<String> encaminhamentos) {
        this.encaminhamentos = encaminhamentos;
    }

    public ProntuarioMedico getProntuarioMedico() {
        return prontuarioMedico;
    }

    public void setProntuarioMedico(ProntuarioMedico prontuarioMedico) {
        this.prontuarioMedico = prontuarioMedico;
    }


    public void registrarDiagnostico(String cid10) {
        this.diagnosticoCID10 = cid10;
        System.out.println("Diagnóstico CID-10 " + cid10 + " registrado.");
    }

    public void gerarEncaminhamento(String detalheEncaminhamento) {
        this.encaminhamentos.add(detalheEncaminhamento);
        System.out.println("Encaminhamento para: " + detalheEncaminhamento + " gerado.");
    }
}