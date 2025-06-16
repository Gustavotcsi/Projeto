package com.ProjetoExtensao.Projeto.config;

import com.ProjetoExtensao.Projeto.models.Consulta;
import com.ProjetoExtensao.Projeto.models.Paciente;
import com.ProjetoExtensao.Projeto.models.ProfissionalSaude;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DatabaseConfig {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    CommandLineRunner initDB(ResponsavelRepositorio responsavelRepositorio, PacienteRepositorio pacienteRepositorio, ConsultaRepositorio consultaRepositorio) {
        return args -> {
            if (!isTablePopulated("pacientes")) {
                System.out.println("Preenchendo o banco de dados...");

                ProfissionalSaude profissionalSaude = responsavelRepositorio.save(new ProfissionalSaude("maria", "123", "Dr. Maria"));

                Paciente paciente = pacienteRepositorio.save(new Paciente("João", "99999999999", LocalDate.parse("1970-03-12"),
                        "Josefina", "999999", LocalDate.now(), profissionalSaude));

                consultaRepositorio.save(new Consulta(LocalDate.now(), LocalTime.now(), "rotina", profissionalSaude, paciente));

                System.out.println("Preenchimento do banco de dados concluído.");
            } else {
                System.out.println("Banco de dados preenchido!");
            }
        };
    }

    private boolean isTablePopulated(String table) {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + table.toLowerCase(), Integer.class);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            System.out.println("Tabela '" + table.toLowerCase() + "' não encontrada.");
            return false;
        }
    }
}