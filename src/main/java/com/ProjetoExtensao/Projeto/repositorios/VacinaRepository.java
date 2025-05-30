package com.ProjetoExtensao.Projeto.repositorios;

import com.ProjetoExtensao.Projeto.entidades.Vacina;
import com.ProjetoExtensao.Projeto.entidades.ProntuarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    List<Vacina> findByProntuarioMedico(ProntuarioMedico prontuarioMedico);
    List<Vacina> findByNomeVacina(String nomeVacina);
    List<Vacina> findByDataAplicacaoBetween(LocalDate startDate, LocalDate endDate);
}