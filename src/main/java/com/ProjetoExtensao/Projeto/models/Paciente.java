package com.ProjetoExtensao.Projeto.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(unique = true)
    private String cpf;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String nomeMae;

    @Column(name = "cartao_sus", unique = true)
    private String cartaoSUS;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataEntrada;


    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private ProfissionalSaude profissionalSaude;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();

    public Paciente(String nomeCompleto, String cpf, LocalDate dataNascimento, String nomeMae, String cartaoSUS, LocalDate dataEntrada, ProfissionalSaude profissionalSaude) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.cartaoSUS = cartaoSUS;
        this.dataEntrada = dataEntrada;
        this.profissionalSaude = profissionalSaude;
    }

    public void addConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.setPaciente(this);
    }
}