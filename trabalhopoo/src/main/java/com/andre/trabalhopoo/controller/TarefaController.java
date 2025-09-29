package com.andre.trabalhopoo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andre.trabalhopoo.dto.TarefaDTO;
import com.andre.trabalhopoo.model.Status;
import com.andre.trabalhopoo.model.Tarefa;
import com.andre.trabalhopoo.service.TarefaService;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    private static final Logger logger = LoggerFactory.getLogger(TarefaController.class);

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Tudo funcionando corretamente");
    }

    @PostMapping
    public ResponseEntity<String> criarTarefa(@RequestBody TarefaDTO data) {
        logger.info("Endpoint CriarTarefa chamado: {}", data);

        try {
            service.registrarTarefa(data);
            logger.info("CriarTarefa realizado com sucesso");
            return ResponseEntity.ok("Valores salvos com sucesso");
        } catch (Exception e) {
            logger.error("Erro em CriarTarefa: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Erro ao criar a tarefa");
        }
    }

    @GetMapping
    public List<Tarefa> listarTodas() {
        logger.info("Endpoint listarTodas chamado");

        try {
            List<Tarefa> tarefas = service.listarTodasTarefa();
            logger.info("listarTodas realizado com sucesso");
            return tarefas;   
        } catch (Exception e) {
            logger.error("Erro em listarTodas", e.getMessage(), e);
            ResponseEntity.internalServerError().body("Erro ao listar as tarefas");
            return null;
        }
    }

    @GetMapping("/status")
    public List<Tarefa> filtrarStatus(@RequestParam Status status) {
        logger.info("Endpoint filtrarStatus chamado");

        try {
            List<Tarefa> tarefas = service.listarStatusTarefa(status);
            logger.info("filtrarStatus realizado com sucesso");
            return tarefas;
        } catch (Exception e) {
            logger.error("Erro em filtrarStatus", e.getMessage(), e);
            ResponseEntity.internalServerError().body("Erro ao listar por status");
            return null;
        }
    }

    @GetMapping("/importante")
    public List<Tarefa> filtrarImportante(@RequestParam boolean importante) {
        logger.info("Endpoint filtrarImportante chamado");

        try {
            List<Tarefa> tarefas = service.listarImportanteTarefa(importante);
            logger.info("filtrarImportante realizado com sucesso");
            return tarefas;
        } catch (Exception e) {
            logger.error("Erro em filtrarImportante", e.getMessage(), e);
            ResponseEntity.internalServerError().body("Erro ao listar por importancia");
            return null;
        }
    }

    @GetMapping("/{id}")
    public Tarefa buscarId(@PathVariable Long id) {
        logger.info("Endpoint buscarId chamado: {}", id);

        try {
            Tarefa tarefa = service.listarTarefa(id);

            if(tarefa == null) {
                logger.warn("ID {} não encontrado", id);
            } else {
                logger.info("Tarefa {} encontrad", id);
            }
            return tarefa;
        } catch (Exception e) {
            logger.error("Erro em buscarId", e.getMessage(), e);
            ResponseEntity.internalServerError().body("Erro ao achar pelo id");
            return null;
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        logger.info("Endpoint atualizarParcial chamado: {}", id);

        try {
            Tarefa novaTarefa = service.atualizarParcialTarefa(id, update);
            logger.info("atualizarParcial realizado com sucesso");
            return ResponseEntity.ok(novaTarefa);
        } catch (Exception e) {
            logger.error("Erro em atualizarParcial", e.getMessage(), e);
            ResponseEntity.internalServerError().body("Erro ao atualizar o id");
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTarefa(@PathVariable Long id) {
        logger.info("Endpoint excluirTarefa chamado: {}", id);

        try {
            service.deletarTarefa(id);
            logger.info("excluirTareda realizado com sucesso");
            return ResponseEntity.ok("Tarefa deletada com sucesso");
        } catch (Exception e) {
            logger.error("Erro em excluirTarefa", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Erro ao excluir a tarefa");
        }
    }
}