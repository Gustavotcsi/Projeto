package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.models.Internacao;
import com.ProjetoExtensao.Projeto.models.ProntuarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
    List<Internacao> findByProntuarioMedico(ProntuarioMedico prontuarioMedico);
    List<Internacao> findByDataEntradaBetween(LocalDate startDate, LocalDate endDate);
    List<Internacao> findByHospitalContainingIgnoreCase(String hospital);
}