package com.Alura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer nascimento;
    private Integer falecimento;
    public Autor(){}
    public Autor(String nome, Integer nascimento, Integer falecimento) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.falecimento = falecimento;
    }
    public String getNome() {
        return nome;
    }
    public Integer getNascimento() {
        return nascimento;
    }
    public Integer getFalecimento() {
        return falecimento;
    }
    @Override
    public String toString() {
        return nome + " (" + nascimento + "-" + falecimento + ")";
    }
}