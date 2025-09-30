package com.poo.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.avaliacao.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    List<Tarefa> findByStatus(String status);
    List<Tarefa> findByImportanteTrue();
}
