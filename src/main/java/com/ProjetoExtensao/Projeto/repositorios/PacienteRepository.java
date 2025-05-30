package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(String cpf);
    List<Paciente> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);
}