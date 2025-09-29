package com.andre.trabalhopoo.model;

public enum Status {
    
    PENDENTE("Pendente"),
    EXECUTANDO("Executando"),
    CONCLUIDO("Concluído");

    private String estado;

    Status(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
