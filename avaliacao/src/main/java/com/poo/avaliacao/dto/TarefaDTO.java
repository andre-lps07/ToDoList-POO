package com.poo.avaliacao.dto;

import java.time.LocalDateTime;

public record TarefaDTO(String nome, String descricao, LocalDateTime dataEntrega, boolean importante, String status) {
    
}
