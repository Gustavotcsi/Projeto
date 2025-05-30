package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.entidades.Exame;
import com.ProjetoExtensao.Projeto.entidades.ProntuarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findByProntuarioMedico(ProntuarioMedico prontuarioMedico);
    List<Exame> findByNomeExame(String nomeExame);
    List<Exame> findByDataSolicitacaoBetween(LocalDate startDate, LocalDate endDate);
    List<Exame> findByDataResultadoNotNull();
}