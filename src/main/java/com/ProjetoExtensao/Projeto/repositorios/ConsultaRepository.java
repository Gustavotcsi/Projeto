package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.models.Consulta;
import com.ProjetoExtensao.Projeto.models.Paciente;
import com.ProjetoExtensao.Projeto.models.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByProntuarioMedico_Paciente(Paciente paciente);
    List<Consulta> findByProfissionalResponsavel(ProfissionalSaude profissionalSaude);
    List<Consulta> findByDataHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Consulta> findByDataHoraBetweenAndProfissionalResponsavel(LocalDateTime startDateTime, LocalDateTime endDateTime, ProfissionalSaude profissionalSaude);
}