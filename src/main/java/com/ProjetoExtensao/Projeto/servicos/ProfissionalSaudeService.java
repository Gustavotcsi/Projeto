package com.ProjetoExtensao.Projeto.servicos;

import com.ProjetoExtensao.Projeto.models.ProfissionalSaude;
import com.ProjetoExtensao.Projeto.repositorios.ProfissionalSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalSaudeService {

    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    public ProfissionalSaude salvarProfissional(ProfissionalSaude profissionalSaude) {
        if (profissionalSaude.getRegistroProfissional() == null || profissionalSaude.getRegistroProfissional().isEmpty()) {
            throw new IllegalArgumentException("Registro profissional não pode ser nulo ou vazio.");
        }
        if (profissionalSaudeRepository.findByRegistroProfissional(profissionalSaude.getRegistroProfissional()) != null) {
            throw new IllegalArgumentException("Já existe um profissional com este registro.");
        }
        return profissionalSaudeRepository.save(profissionalSaude);
    }

    public List<ProfissionalSaude> listarTodosProfissionais() {
        return profissionalSaudeRepository.findAll();
    }

    public Optional<ProfissionalSaude> buscarProfissionalPorId(Long id) {
        return profissionalSaudeRepository.findById(id);
    }

    public ProfissionalSaude buscarProfissionalPorRegistro(String registro) {
        return profissionalSaudeRepository.findByRegistroProfissional(registro);
    }

    public ProfissionalSaude atualizarProfissional(Long id, ProfissionalSaude profissionalAtualizado) {
        return profissionalSaudeRepository.findById(id).map(profissionalExistente -> {
            profissionalExistente.setNome(profissionalAtualizado.getNome());
            profissionalExistente.setEspecialidade(profissionalAtualizado.getEspecialidade());
            profissionalExistente.setRegistroProfissional(profissionalAtualizado.getRegistroProfissional());
            return profissionalSaudeRepository.save(profissionalExistente);
        }).orElseThrow(() -> new RuntimeException("Profissional de saúde não encontrado com o ID: " + id));
    }

    public void deletarProfissional(Long id) {
        if (!profissionalSaudeRepository.existsById(id)) {
            throw new RuntimeException("Profissional de saúde não encontrado com o ID: " + id);
        }
        profissionalSaudeRepository.deleteById(id);
    }
}