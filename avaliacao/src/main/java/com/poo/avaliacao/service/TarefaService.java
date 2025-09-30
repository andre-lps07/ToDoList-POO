package com.poo.avaliacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.avaliacao.dto.TarefaDTO;
import com.poo.avaliacao.model.Tarefa;
import com.poo.avaliacao.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository repository;

    public void adicionar(TarefaDTO dto){
        if (dto.status() == null) {
            Tarefa tarefa  = new Tarefa(dto.nome(), dto.descricao(), dto.dataEntrega(), dto.importante(), "A_FAZER");
            repository.save(tarefa);
            
        } else {
            Tarefa tarefa  = new Tarefa(dto.nome(), dto.descricao(), dto.dataEntrega(), dto.importante(), dto.status());
            repository.save(tarefa);
        }
        
    }

    public List<Tarefa> listarTodos(){
        return repository.findAll();
    }

    public Tarefa listarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void atualizar(Long id, TarefaDTO dto){
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setDescricao(dto.descricao());
        tarefa.setStatus(dto.status());
        repository.save(tarefa);
    }

    public void remover(Long id){
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        repository.delete(tarefa);
    }

    public List<Tarefa> listarPorStatus(String status){
        return repository.findByStatus(status);
    }

    public List<Tarefa> listarPorImportancia(Boolean importancia){
        return repository.findByImportanteTrue();
    }
}
