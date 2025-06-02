package com.microservicio.validador.controller;

import com.microservicio.validador.dto.ValidacionRequest;
import com.microservicio.validador.dto.ValidacionResponse;
import com.microservicio.validador.service.ValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para el microservicio validador de llaves
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Para permitir acceso desde el frontend
public class ValidadorController {

    @Autowired
    private ValidadorService validadorService;

    /**
     * Endpoint principal para validar secuencias de llaves
     * POST /api/validate-braces
     */
    @PostMapping("/validate-braces")
    public ResponseEntity<ValidacionResponse> validateBraces(@RequestBody ValidacionRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            String sequence = request.getSequence();
            boolean isValid = validadorService.validateBraces(sequence);

            long processingTime = System.currentTimeMillis() - startTime;

            String message = isValid ?
                    "Secuencia válida: todas las llaves están correctamente balanceadas" :
                    "Secuencia inválida: las llaves no están correctamente balanceadas";

            ValidacionResponse response = new ValidacionResponse(
                    isValid,
                    message,
                    sequence,
                    processingTime
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            long processingTime = System.currentTimeMillis() - startTime;

            ValidacionResponse errorResponse = new ValidacionResponse(
                    false,
                    "Error al procesar la secuencia: " + e.getMessage(),
                    request.getSequence(),
                    processingTime
            );

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Endpoint alternativo usando el método for-each
     * GET /api/validate-braces-foreach?sequence={sequence}
     */
    @GetMapping("/validate-braces-foreach")
    public ResponseEntity<ValidacionResponse> validateBracesForEach(@RequestParam String sequence) {
        long startTime = System.currentTimeMillis();

        try {
            boolean isValid = validadorService.validateBracesForEach(sequence);
            long processingTime = System.currentTimeMillis() - startTime;

            String message = isValid ?
                    "Secuencia válida (método for-each)" :
                    "Secuencia inválida (método for-each)";

            ValidacionResponse response = new ValidacionResponse(
                    isValid,
                    message,
                    sequence,
                    processingTime
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            long processingTime = System.currentTimeMillis() - startTime;

            ValidacionResponse errorResponse = new ValidacionResponse(
                    false,
                    "Error: " + e.getMessage(),
                    sequence,
                    processingTime
            );

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Endpoint con información detallada del proceso de validación
     * POST /api/validate-braces-detailed
     */
    @PostMapping("/validate-braces-detailed")
    public ResponseEntity<DetailedValidacionResponse> validateBracesDetailed(@RequestBody ValidacionRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            ValidadorService.ValidationResult result = validadorService.validateWithDetails(request.getSequence());
            long processingTime = System.currentTimeMillis() - startTime;

            DetailedValidacionResponse response = new DetailedValidacionResponse(
                    result.isValid(),
                    result.getMessage(),
                    request.getSequence(),
                    processingTime,
                    result.getOpenBraces(),
                    result.getCloseBraces()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            long processingTime = System.currentTimeMillis() - startTime;

            DetailedValidacionResponse errorResponse = new DetailedValidacionResponse(
                    false,
                    "Error: " + e.getMessage(),
                    request.getSequence(),
                    processingTime,
                    0,
                    0
            );

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Endpoint de salud del servicio
     * GET /api/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Microservicio Validador de Llaves funcionando correctamente");
    }

    /**
     * DTO para respuesta detallada
     */
    public static class DetailedValidacionResponse extends ValidacionResponse {
        private int openBraces;
        private int closeBraces;

        public DetailedValidacionResponse(boolean isValid, String message, String sequence,
                                          long processingTimeMs, int openBraces, int closeBraces) {
            super(isValid, message, sequence, processingTimeMs);
            this.openBraces = openBraces;
            this.closeBraces = closeBraces;
        }

        public int getOpenBraces() { return openBraces; }
        public void setOpenBraces(int openBraces) { this.openBraces = openBraces; }

        public int getCloseBraces() { return closeBraces; }
        public void setCloseBraces(int closeBraces) { this.closeBraces = closeBraces; }
    }
}
