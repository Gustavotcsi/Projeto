package com.ProjetoExtensao.Projeto.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "prontuarios_medicos")
public class ProntuarioMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false, unique = true)
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuarioMedico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "prontuarioMedico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Prescricao> prescricoes = new ArrayList<>();

    @OneToMany(mappedBy = "prontuarioMedico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Exame> exames = new ArrayList<>();

    @OneToMany(mappedBy = "prontuarioMedico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Internacao> historicoInternacoes = new ArrayList<>();

    @OneToMany(mappedBy = "prontuarioMedico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Vacina> historicoVacinacao = new ArrayList<>();

    public ProntuarioMedico() {}

    public ProntuarioMedico(Paciente paciente) {
        this.paciente = paciente;
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

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public List<Internacao> getHistoricoInternacoes() {
        return historicoInternacoes;
    }

    public void setHistoricoInternacoes(List<Internacao> historicoInternacoes) {
        this.historicoInternacoes = historicoInternacoes;
    }

    public List<Vacina> getHistoricoVacinacao() {
        return historicoVacinacao;
    }

    public void setHistoricoVacinacao(List<Vacina> historicoVacinacao) {
        this.historicoVacinacao = historicoVacinacao;
    }


    public void adicionarNovaConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.setProntuarioMedico(this);
        System.out.println("Consulta adicionada para " + paciente.getNomeCompleto() + " em " + consulta.getDataHora());
    }

    public void removerConsulta(Consulta consulta) {
        this.consultas.remove(consulta);
        consulta.setProntuarioMedico(null);
    }

    public void adicionarPrescricao(Prescricao prescricao) {
        this.prescricoes.add(prescricao);
        prescricao.setProntuarioMedico(this);
        System.out.println("Prescrição de " + prescricao.getMedicamento() + " adicionada para " + paciente.getNomeCompleto());
    }

    public void adicionarExame(Exame exame) {
        this.exames.add(exame);
        exame.setProntuarioMedico(this);
        System.out.println("Exame de " + exame.getNomeExame() + " adicionado para " + paciente.getNomeCompleto());
    }

    public void adicionarInternacao(Internacao internacao) {
        this.historicoInternacoes.add(internacao);
        internacao.setProntuarioMedico(this);
        System.out.println("Internação em " + internacao.getDataEntrada() + " adicionada para " + paciente.getNomeCompleto());
    }

    public void adicionarVacina(Vacina vacina) {
        this.historicoVacinacao.add(vacina);
        vacina.setProntuarioMedico(this);
        System.out.println("Vacina " + vacina.getNomeVacina() + " aplicada em " + vacina.getDataAplicacao() + " para " + paciente.getNomeCompleto());
    }

    public void vincularResultadoDeExame(Long idExame, LocalDate dataResultado, String resultado) {
        for (Exame exame : exames) {
            if (exame.getId() != null && exame.getId().equals(idExame)) {
                exame.vincularResultado(dataResultado, resultado);
                return;
            }
        }
        System.out.println("Exame com ID " + idExame + " não encontrado para vincular resultado.");
    }

    public String gerarResumoHistorico() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("--- Resumo do Prontuário de ").append(paciente.getNomeCompleto()).append(" ---\n");
        resumo.append("Data de Nascimento: ").append(paciente.getDataNascimento()).append("\n");
        resumo.append("Cartão SUS: ").append(paciente.getCartaoSUS()).append("\n\n");

        resumo.append("Histórico de Consultas:\n");
        if (consultas.isEmpty()) {
            resumo.append("  Nenhuma consulta registrada.\n");
        } else {
            consultas.forEach(c -> resumo.append("  - Data: ").append(c.getDataHora().toLocalDate())
                    .append(", Profissional: ").append(c.getProfissionalResponsavel().getNome())
                    .append(", Tipo: ").append(c.getTipo())
                    .append(", Diagnóstico: ").append(c.getDiagnosticoCID10()).append("\n"));
        }
        resumo.append("\n");

        resumo.append("Histórico de Prescrições:\n");
        if (prescricoes.isEmpty()) {
            resumo.append("  Nenhuma prescrição registrada.\n");
        } else {
            prescricoes.forEach(p -> resumo.append("  - Medicamento: ").append(p.getMedicamento())
                    .append(", Dosagem: ").append(p.getDosagem())
                    .append(", Início: ").append(p.getDataInicio())
                    .append(p.getDataFim() != null ? ", Fim: " + p.getDataFim() : "")
                    .append(")\n"));
        }
        resumo.append("\n");

        resumo.append("Histórico de Exames:\n");
        if (exames.isEmpty()) {
            resumo.append("  Nenhum exame registrado.\n");
        } else {
            exames.forEach(e -> resumo.append("  - Exame: ").append(e.getNomeExame())
                    .append(", Solicitado em: ").append(e.getDataSolicitacao())
                    .append(", Resultado: ").append(e.getResultado() != null ? "Sim" : "Não").append("\n"));
        }
        resumo.append("\n");

        resumo.append("Histórico de Internações:\n");
        if (historicoInternacoes.isEmpty()) {
            resumo.append("  Nenhuma internação registrada.\n");
        } else {
            historicoInternacoes.forEach(i -> resumo.append("  - Entrada: ").append(i.getDataEntrada())
                    .append(", Saída: ").append(i.getDataSaida())
                    .append(", Motivo: ").append(i.getMotivoInternacao()).append("\n"));
        }
        resumo.append("\n");

        resumo.append("Histórico de Vacinação:\n");
        if (historicoVacinacao.isEmpty()) {
            resumo.append("  Nenhuma vacina registrada.\n");
        } else {
            historicoVacinacao.forEach(v -> resumo.append("  - Vacina: ").append(v.getNomeVacina())
                    .append(", Aplicação: ").append(v.getDataAplicacao()).append("\n"));
        }
        resumo.append("-------------------------------------------\n");
        return resumo.toString();
    }

    public List<Consulta> buscarConsultas(LocalDate data, String nomeProfissional) {
        return consultas.stream()
                .filter(c -> (data == null || c.getDataHora().toLocalDate().equals(data)) &&
                        (nomeProfissional == null || c.getProfissionalResponsavel().getNome().equalsIgnoreCase(nomeProfissional)))
                .collect(Collectors.toList());
    }
}
