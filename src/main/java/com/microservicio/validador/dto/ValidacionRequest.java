package com.microservicio.validador.dto;

/**
 * DTO para la petición de validación
 */
public class ValidacionRequest {
    private String sequence;

    public ValidacionRequest() {}

    public ValidacionRequest(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "ValidacionRequest{sequence='" + sequence + "'}";
    }
}
