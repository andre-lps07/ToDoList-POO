package com.andre.trabalhopoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.trabalhopoo.model.Status;
import com.andre.trabalhopoo.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByStatus(Status status);
    List<Tarefa> findByImportante(boolean importante);
}
