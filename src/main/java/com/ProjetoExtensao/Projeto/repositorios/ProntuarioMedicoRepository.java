package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.entidades.ProntuarioMedico;
import com.ProjetoExtensao.Projeto.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProntuarioMedicoRepository extends JpaRepository<ProntuarioMedico, Long> {
    Optional<ProntuarioMedico> findByPaciente(Paciente paciente);
}