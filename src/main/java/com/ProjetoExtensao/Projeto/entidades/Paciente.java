package com.ProjetoExtensao.Projeto.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    public Paciente(String nomeCompleto, String cpf, LocalDate dataNascimento, String nomeMae, String cartaoSUS, LocalDate dataEntrada, ResponsavelSaude responsavelSaude) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.cartaoSUS = cartaoSUS;
        this.dataEntrada = dataEntrada;
        this.responsavelSaude = responsavelSaude;
    }

    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
}