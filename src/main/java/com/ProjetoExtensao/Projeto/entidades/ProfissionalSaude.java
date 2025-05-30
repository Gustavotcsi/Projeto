package com.ProjetoExtensao.Projeto.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "profissionais_saude")
public class ProfissionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @Column(name = "registro_profissional", unique = true, length = 50)
    private String registroProfissional;

    public ProfissionalSaude() {}

    public ProfissionalSaude(String nome, String especialidade, String registroProfissional) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }
}
