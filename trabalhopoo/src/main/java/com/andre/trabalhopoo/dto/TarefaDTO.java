package com.andre.trabalhopoo.dto;

import java.time.LocalDate;

import com.andre.trabalhopoo.model.Status;

public record TarefaDTO(String nome, String descricao, Status status, LocalDate dataEntrega, boolean importante) {
    
}
