package com.microservicio.validador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para la respuesta de validación
 */
public class ValidacionResponse {
    @JsonProperty("isValid")
    private boolean isValid;
    private String message;
    private String sequence;
    private long processingTimeMs;

    public ValidacionResponse() {}

    public ValidacionResponse(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public ValidacionResponse(boolean isValid, String message, String sequence) {
        this.isValid = isValid;
        this.message = message;
        this.sequence = sequence;
    }

    public ValidacionResponse(boolean isValid, String message, String sequence, long processingTimeMs) {
        this.isValid = isValid;
        this.message = message;
        this.sequence = sequence;
        this.processingTimeMs = processingTimeMs;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public long getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    @Override
    public String toString() {
        return "ValidacionResponse{" +
                "isValid=" + isValid +
                ", message='" + message + '\'' +
                ", sequence='" + sequence + '\'' +
                ", processingTimeMs=" + processingTimeMs +
                '}';
    }
}
