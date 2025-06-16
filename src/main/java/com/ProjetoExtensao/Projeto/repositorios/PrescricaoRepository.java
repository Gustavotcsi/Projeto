package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.models.Prescricao;
import com.ProjetoExtensao.Projeto.models.ProntuarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {
    List<Prescricao> findByProntuarioMedico(ProntuarioMedico prontuarioMedico);
    List<Prescricao> findByMedicamentoContainingIgnoreCase(String medicamento);
    List<Prescricao> findByDataInicioBetween(LocalDate startDate, LocalDate endDate);
}