package com.ProjetoExtensao.Projeto.servicos;

import com.ProjetoExtensao.Projeto.models.Consulta;
import com.ProjetoExtensao.Projeto.repositorios.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta salvarConsulta(Consulta consulta) {
        if (consulta.getDataHora() == null) {
            throw new IllegalArgumentException("A data da consulta é obrigatória.");
        }
        if (consulta.getProfissionalResponsavel() == null) {
            throw new IllegalArgumentException("Profissional de saúde é obrigatório.");
        }
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta atualizarConsulta(Long id, Consulta novaConsulta) {
        return consultaRepository.findById(id).map(consultaExistente -> {
            consultaExistente.setDataHora(novaConsulta.getDataHora());
            consultaExistente.setProfissionalResponsavel(novaConsulta.getProfissionalResponsavel());
            consultaExistente.setTipo(novaConsulta.getTipo());
            consultaExistente.setMotivoConsulta(novaConsulta.getMotivoConsulta());
            consultaExistente.setDiagnosticoCID10(novaConsulta.getDiagnosticoCID10());
            consultaExistente.setAnotacoesProfissional(novaConsulta.getAnotacoesProfissional());
            consultaExistente.setEncaminhamentos(novaConsulta.getEncaminhamentos());
            return consultaRepository.save(consultaExistente);
        }).orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));
    }

    public void deletarConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com o ID: " + id);
        }
        consultaRepository.deleteById(id);
    }
}
