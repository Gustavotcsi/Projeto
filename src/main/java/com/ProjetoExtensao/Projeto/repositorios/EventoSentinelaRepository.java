package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.models.EventoSentinela;
import com.ProjetoExtensao.Projeto.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoSentinelaRepository extends JpaRepository<EventoSentinela, Long> {
    List<EventoSentinela> findByPaciente(Paciente paciente);
    List<EventoSentinela> findByDescricaoEventoContainingIgnoreCase(String descricaoEvento);
    List<EventoSentinela> findByDataOcorrenciaBetween(LocalDate startDate, LocalDate endDate);
}