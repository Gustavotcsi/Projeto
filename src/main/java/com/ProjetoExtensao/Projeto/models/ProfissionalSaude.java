package com.ProjetoExtensao.Projeto.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "responsaveis_saude")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProfissionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nomeCompleto;

    @OneToMany(mappedBy = "responsavelSaude")
    private List<Paciente> pacientes;

    @OneToMany(mappedBy = "responsavelSaude")
    private List<Consulta> consultas;

    public ProfissionalSaude(String username, String senha, String nomeCompleto) {
        this.username = username;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
    }

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
}