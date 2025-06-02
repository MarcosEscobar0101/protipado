package com.microservicio.validador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del microservicio validador de llaves
 */
@SpringBootApplication
public class MicroservicioValidadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioValidadorApplication.class, args);
        System.out.println("=".repeat(60));
        System.out.println("🚀 MICROSERVICIO VALIDADOR DE LLAVES INICIADO");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("📋 Endpoints disponibles:");
        System.out.println("   POST /api/validate-braces");
        System.out.println("   GET  /api/validate-braces-foreach?sequence={sequence}");
        System.out.println("   POST /api/validate-braces-detailed");
        System.out.println("   GET  /api/health");
        System.out.println("🌐 Frontend: http://localhost:8080/index.html");
        System.out.println("=".repeat(60));
    }
}
