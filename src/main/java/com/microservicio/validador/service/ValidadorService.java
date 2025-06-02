package com.microservicio.validador.service;

import com.microservicio.validador.util.CustomStack;
import org.springframework.stereotype.Service;

/**
 * Servicio que contiene la lógica de validación de llaves
 * Utiliza una implementación optimizada con complejidad O(n)
 */
@Service
public class ValidadorService {

    /**
     * Valida si una secuencia de llaves está correctamente balanceada
     * Utiliza charAt() para recorrer la cadena según requerimientos
     *
     * @param sequence cadena con llaves { y }
     * @return true si está balanceada, false en caso contrario
     */
    public boolean validateBraces(String sequence) {
        // Validación de entrada
        if (sequence == null) {
            return true; // null se considera válido (cadena vacía)
        }

        // Crear stack personalizado para trackear llaves abiertas
        CustomStack stack = new CustomStack(sequence.length());

        // Recorrer cada caracter usando charAt() como se requiere
        for (int i = 0; i < sequence.length(); i++) {
            char currentChar = sequence.charAt(i);

            if (currentChar == '{') {
                // Llave de apertura: push al stack
                stack.push(currentChar);
            } else if (currentChar == '}') {
                // Llave de cierre: verificar si hay apertura correspondiente
                if (stack.isEmpty()) {
                    return false; // Llave de cierre sin apertura
                }
                stack.pop(); // Remover la llave de apertura correspondiente
            } else if (currentChar != ' ') {
                // Caracter no válido (que no sea espacio)
                return false;
            }
            // Los espacios se ignoran
        }

        // La secuencia es válida solo si no quedan llaves sin cerrar
        return stack.isEmpty();
    }

    /**
     * Implementación alternativa usando sintaxis for-each
     * Se recorre un array de caracteres convertido desde la cadena
     *
     * @param sequence cadena con llaves { y }
     * @return true si está balanceada, false en caso contrario
     */
    public boolean validateBracesForEach(String sequence) {
        if (sequence == null) {
            return true;
        }

        CustomStack stack = new CustomStack(sequence.length());

        // Convertir string a array para usar for-each
        char[] characters = sequence.toCharArray();

        // Usar sintaxis for-each según requerimientos alternativos
        for (char currentChar : characters) {
            if (currentChar == '{') {
                stack.push(currentChar);
            } else if (currentChar == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            } else if (currentChar != ' ') {
                return false;
            }
        }

        return stack.isEmpty();
    }

    /**
     * Validación con información detallada para debugging
     *
     * @param sequence cadena a validar
     * @return objeto con resultado y detalles del proceso
     */
    public ValidationResult validateWithDetails(String sequence) {
        if (sequence == null) {
            return new ValidationResult(true, "Secuencia nula considerada válida", 0, 0);
        }

        CustomStack stack = new CustomStack(sequence.length());
        int openBraces = 0;
        int closeBraces = 0;

        for (int i = 0; i < sequence.length(); i++) {
            char currentChar = sequence.charAt(i);

            if (currentChar == '{') {
                stack.push(currentChar);
                openBraces++;
            } else if (currentChar == '}') {
                closeBraces++;
                if (stack.isEmpty()) {
                    return new ValidationResult(false,
                            String.format("Llave de cierre sin apertura en posición %d", i),
                            openBraces, closeBraces);
                }
                stack.pop();
            } else if (currentChar != ' ') {
                return new ValidationResult(false,
                        String.format("Caracter inválido '%c' en posición %d", currentChar, i),
                        openBraces, closeBraces);
            }
        }

        boolean isValid = stack.isEmpty();
        String message = isValid ?
                "Secuencia correctamente balanceada" :
                String.format("Quedan %d llaves sin cerrar", stack.size());

        return new ValidationResult(isValid, message, openBraces, closeBraces);
    }

    /**
     * Clase interna para resultados detallados
     */
    public static class ValidationResult {
        private final boolean valid;
        private final String message;
        private final int openBraces;
        private final int closeBraces;

        public ValidationResult(boolean valid, String message, int openBraces, int closeBraces) {
            this.valid = valid;
            this.message = message;
            this.openBraces = openBraces;
            this.closeBraces = closeBraces;
        }

        // Getters
        public boolean isValid() { return valid; }
        public String getMessage() { return message; }
        public int getOpenBraces() { return openBraces; }
        public int getCloseBraces() { return closeBraces; }
    }
}