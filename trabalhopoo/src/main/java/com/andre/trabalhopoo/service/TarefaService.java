package com.andre.trabalhopoo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.trabalhopoo.dto.TarefaDTO;
import com.andre.trabalhopoo.model.Status;
import com.andre.trabalhopoo.model.Tarefa;
import com.andre.trabalhopoo.repository.TarefaRepository;

@Service
public class TarefaService {
 
    @Autowired
    private TarefaRepository repository;

    public void registrarTarefa(TarefaDTO data) {
        Tarefa tarefa = new Tarefa(data.nome(), data.descricao(), data.status(), data.dataEntrega(), data.importante());
        repository.save(tarefa);
    }

    public List<Tarefa> listarTodasTarefa() {
        return repository.findAll();
    }

    public List<Tarefa> listarStatusTarefa(Status status) {
        return repository.findByStatus(status);
    }

    public List<Tarefa> listarImportanteTarefa(boolean importante) {
        return repository.findByImportante(importante);
    }

    public Tarefa listarTarefa(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    // PATCH
    public Tarefa atualizarParcialTarefa(Long id, Map<String, Object> update) {
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if(update.containsKey("nome")) {
            tarefa.setNome(update.get("nome").toString());
        }
        if(update.containsKey("descricao")) {
            tarefa.setDescricao(update.get("descricao").toString());
        }
        if(update.containsKey("status")) {
            tarefa.setStatus(Status.valueOf(update.get("status").toString()));
        }
        if(update.containsKey("dataEntrega")) {
            tarefa.setDataEntrega(LocalDate.parse(update.get("dataEntrega").toString()));
        }
        if(update.containsKey("importante")) {
            tarefa.setImportante(Boolean.parseBoolean(update.get("importante").toString()));
        }
        return repository.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        repository.deleteById(id);
    }
}
