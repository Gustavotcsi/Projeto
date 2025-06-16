package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.models.ProntuarioMedico;
import com.ProjetoExtensao.Projeto.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProntuarioMedicoRepository extends JpaRepository<ProntuarioMedico, Long> {
    Optional<ProntuarioMedico> findByPaciente(Paciente paciente);
}