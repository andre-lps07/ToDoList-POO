package com.poo.avaliacao.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
    
    public Tarefa() {
    }

    public Tarefa(String nome, String descricao, LocalDateTime dataEntrega, boolean importante, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.importante = importante;
        this.dataCriacao = LocalDateTime.now();
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = true)
    private LocalDateTime dataEntrega;

    @Column(nullable = true)
    private LocalDateTime dataCriacao;

    @Column(nullable = true)
    private String status = "A_FAZER";

    @Column(nullable = true)
    private boolean importante;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
