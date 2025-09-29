package com.andre.trabalhopoo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private LocalDate criacao;

    private String nome;

    private String descricao;

    private Status status;

    private LocalDate dataEntrega;

    private boolean importante;

    public Tarefa(String nome, String descricao, Status status, LocalDate dataEntrega, boolean importante) {
        this.nome = nome;
        this.descricao = descricao;
        if(status == null) {
            this.status = Status.PENDENTE;
        } else {
            this.status = status;
        }
        this.dataEntrega = dataEntrega;
        this.importante = importante;
    }

    @PrePersist
    private void getDate() {
        criacao = LocalDate.now();
    }
}
