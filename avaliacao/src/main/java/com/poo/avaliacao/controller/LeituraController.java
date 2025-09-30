package com.poo.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.avaliacao.dto.TarefaDTO;
import com.poo.avaliacao.model.Tarefa;
import com.poo.avaliacao.service.TarefaService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/")
public class LeituraController {

    @Autowired
    private TarefaService service;

    @PostMapping("tarefas")
    public ResponseEntity<String> adicionarTarefa(@RequestBody TarefaDTO dto) {
        service.adicionar(dto);
        return ResponseEntity.ok("tarefa adicionada com sucesso");
    }

    @GetMapping("/tarefas")
    public List<Tarefa> listarTarefas(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean importancia) {

        if (status != null) {
            return service.listarPorStatus(status);
        } else if (importancia != null) {
            return service.listarPorImportancia(importancia);
        } else {
            return service.listarTodos();
        }
    }

    @GetMapping("tarefas/{id}")
    public Tarefa listarTarefaPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @PatchMapping("tarefas/{id}")
    public ResponseEntity<String> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.ok("tarefa atualizada com sucesso");
    }

    @DeleteMapping("tarefas/{id}")
    public ResponseEntity<String> removerTarefa(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok("tarefa removida com sucesso");
    }

}
