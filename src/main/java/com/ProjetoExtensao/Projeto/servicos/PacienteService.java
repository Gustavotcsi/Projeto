package com.ProjetoExtensao.Projeto.servicos;

import com.ProjetoExtensao.Projeto.entidades.Paciente;
import com.ProjetoExtensao.Projeto.repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired // O Spring injeta uma instância de PacienteRepository aqui
    private PacienteRepository pacienteRepository;

    public Paciente salvarPaciente(Paciente paciente) {
        // Você pode adicionar lógicas de validação aqui antes de salvar
        if (paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF da paciente não pode ser nulo ou vazio.");
        }
        // Exemplo: verificar se o CPF já existe
        if (pacienteRepository.findByCpf(paciente.getCpf()) != null) {
            throw new IllegalArgumentException("Já existe uma paciente cadastrada com este CPF.");
        }
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodasPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public Paciente atualizarPaciente(Long id, Paciente pacienteAtualizada) {
        return pacienteRepository.findById(id).map(pacienteExistente -> {
            pacienteExistente.setNomeCompleto(pacienteAtualizada.getNomeCompleto());
            pacienteExistente.setCpf(pacienteAtualizada.getCpf());
            pacienteExistente.setDataNascimento(pacienteAtualizada.getDataNascimento());
            pacienteExistente.setNomeMae(pacienteAtualizada.getNomeMae());
            pacienteExistente.setCartaoSUS(pacienteAtualizada.getCartaoSUS());
            pacienteExistente.setDataEntradaCasa(pacienteAtualizada.getDataEntradaCasa());
            return pacienteRepository.save(pacienteExistente);
        }).orElseThrow(() -> new RuntimeException("Paciente não encontrada com o ID: " + id));
    }

    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrada com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}