package com.ProjetoExtensao.Projeto.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 255)
    private String nomeCompleto;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nome_mae", nullable = false, length = 255)
    private String nomeMae;

    @Column(name = "cartao_sus", unique = true, length = 15)
    private String cartaoSUS;

    @Column(name = "data_entrada_casa", nullable = false)
    private LocalDate dataEntradaCasa;

    public Paciente() {}

    public Paciente(String nomeCompleto, String cpf, LocalDate dataNascimento,
                    String nomeMae, String cartaoSUS, LocalDate dataEntradaCasa) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.cartaoSUS = cartaoSUS;
        this.dataEntradaCasa = dataEntradaCasa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    public LocalDate getDataEntradaCasa() {
        return dataEntradaCasa;
    }

    public void setDataEntradaCasa(LocalDate dataEntradaCasa) {
        this.dataEntradaCasa = dataEntradaCasa;
    }


    public int calcularIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
}
