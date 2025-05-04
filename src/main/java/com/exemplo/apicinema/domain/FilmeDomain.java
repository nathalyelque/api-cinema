package com.exemplo.apicinema.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_FILME")
public class FilmeDomain implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFilme;
    private String nome;
    private String classificacao;
    private String genero;
    @OneToOne
    @JoinColumn(name = "id_diretor")
    private DiretorDomain diretor;
    private int ano;

    public UUID getIdFilme() {
        return idFilme;
    }

    public String getNome() {
        return nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getGenero() {
        return genero;
    }

    public DiretorDomain getDiretor() {
        return diretor;
    }

    public void setIdFilme(UUID idFilme) {
        this.idFilme = idFilme;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDiretor(DiretorDomain diretor) {
        this.diretor = diretor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
