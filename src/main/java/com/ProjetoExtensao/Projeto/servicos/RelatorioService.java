package com.ProjetoExtensao.Projeto.servicos;

import com.ProjetoExtensao.Projeto.entidades.Paciente;
import com.ProjetoExtensao.Projeto.entidades.ProntuarioMedico;
import com.ProjetoExtensao.Projeto.entidades.EventoSentinela;
import com.ProjetoExtensao.Projeto.repositorios.PacienteRepository;
import com.ProjetoExtensao.Projeto.repositorios.ProntuarioMedicoRepository;
import com.ProjetoExtensao.Projeto.repositorios.EventoSentinelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {


    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ProntuarioMedicoRepository prontuarioMedicoRepository;
    @Autowired
    private EventoSentinelaRepository eventoSentinelaRepository;


    private RelatorioPeriodo relatorioPeriodo = new RelatorioPeriodo();


    public String gerarRelatorioCompletoIdosa(Long pacienteId) {

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrada para gerar relatório. ID: " + pacienteId));


        ProntuarioMedico prontuario = prontuarioMedicoRepository.findByPaciente(paciente)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para a paciente. ID: " + pacienteId));


        return relatorioPeriodo.gerarRelatorioIdosaCompleto(prontuario);
    }


    public double calcularPercentualVacinacaoGeral(String nomeVacina) {

        List<ProntuarioMedico> todosOsProntuarios = prontuarioMedicoRepository.findAll();


        return relatorioPeriodo.calcularPercentualVacinacao(todosOsProntuarios, nomeVacina);
    }


    public double calcularPercentualIncidentes(String descricaoEvento) {

        List<Paciente> todasAsPacientes = pacienteRepository.findAll();

        List<EventoSentinela> todosOsEventosSentinela = eventoSentinelaRepository.findAll();


        return relatorioPeriodo.calcularPercentualIdosasComEvento(todasAsPacientes, todosOsEventosSentinela, descricaoEvento);
    }


}