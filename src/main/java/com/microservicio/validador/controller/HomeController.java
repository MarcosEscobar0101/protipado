package com.microservicio.validador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para el endpoint raíz /
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Microservicio Validador de Llaves - Servicio Activo";
    }
}
