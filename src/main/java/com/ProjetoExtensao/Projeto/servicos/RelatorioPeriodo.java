package com.ProjetoExtensao.Projeto.servicos;

import com.ProjetoExtensao.Projeto.entidades.Paciente;
import com.ProjetoExtensao.Projeto.entidades.ProntuarioMedico;
import com.ProjetoExtensao.Projeto.entidades.Vacina;
import com.ProjetoExtensao.Projeto.entidades.EventoSentinela;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RelatorioPeriodo {


    public String gerarRelatorioIdosaCompleto(ProntuarioMedico prontuario) {
        if (prontuario == null || prontuario.getPaciente() == null) {
            return "Prontuário ou paciente inválido.";
        }

        Paciente paciente = prontuario.getPaciente();
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("--- RELATÓRIO COMPLETO DA IDOSA ---\n");
        relatorio.append("Nome: ").append(paciente.getNomeCompleto()).append("\n");
        relatorio.append("CPF: ").append(paciente.getCpf()).append("\n");
        relatorio.append("Data de Nascimento: ").append(paciente.getDataNascimento()).append(" (Idade: ").append(paciente.calcularIdade()).append(" anos)\n");
        relatorio.append("Cartão do SUS: ").append(paciente.getCartaoSUS()).append("\n");
        relatorio.append("Data de Entrada na Casa: ").append(paciente.getDataEntradaCasa()).append("\n\n");

        relatorio.append("--- Medicamentos Atuais ---\n");
        if (prontuario.getPrescricoes().isEmpty()) {
            relatorio.append("Nenhum medicamento registrado.\n");
        } else {
            prontuario.getPrescricoes().forEach(p -> relatorio.append("- ").append(p.getMedicamento())
                    .append(" - Dosagem: ").append(p.getDosagem())
                    .append(" - Frequência: ").append(p.getFrequencia())
                    .append(" (Início: ").append(p.getDataInicio())
                    .append(p.getDataFim() != null ? ", Fim: " + p.getDataFim() : "")
                    .append(")\n"));
        }
        relatorio.append("\n");

        relatorio.append("--- Vacinas Tomadas ---\n");
        if (prontuario.getHistoricoVacinacao().isEmpty()) {
            relatorio.append("Nenhuma vacina registrada.\n");
        } else {
            prontuario.getHistoricoVacinacao().forEach(v -> relatorio.append("- ").append(v.getNomeVacina())
                    .append(" (Aplicação: ").append(v.getDataAplicacao()).append(")\n"));
        }
        relatorio.append("\n");

        relatorio.append("-------------------------------------------\n");
        return relatorio.toString();
    }

    public double calcularPercentualVacinacao(List<ProntuarioMedico> todosOsProntuarios, String nomeVacina) {
        if (todosOsProntuarios == null || todosOsProntuarios.isEmpty()) {
            return 0.0;
        }

        long totalIdosas = todosOsProntuarios.size();
        long idosasVacinadas = todosOsProntuarios.stream()
                .filter(p -> p.getHistoricoVacinacao().stream()
                        .anyMatch(v -> v.getNomeVacina().equalsIgnoreCase(nomeVacina)))
                .count();

        return (double) idosasVacinadas / totalIdosas * 100;
    }


    public double calcularPercentualIdosasComEvento(List<Paciente> todasAsIdosas,
                                                    List<EventoSentinela> todosOsEventosSentinela,
                                                    String descricaoEvento) {
        if (todasAsIdosas == null || todasAsIdosas.isEmpty()) {
            return 0.0;
        }
        if (todosOsEventosSentinela == null || todosOsEventosSentinela.isEmpty()) {
            return 0.0; // Se não há eventos, o percentual é 0
        }

        long totalIdosas = todasAsIdosas.size();

        Set<Long> idsIdosasComEvento = todosOsEventosSentinela.stream()
                .filter(es -> descricaoEvento == null || es.getDescricaoEvento().equalsIgnoreCase(descricaoEvento))
                .map(es -> es.getPaciente().getId()) // Pega o ID da paciente associada ao evento
                .collect(Collectors.toSet()); // Coleta em um Set para garantir IDs únicos

        long idosasAfetadasUnicas = idsIdosasComEvento.size();

        return (double) idosasAfetadasUnicas / totalIdosas * 100;
    }
}