package com.proyectofinaldnd.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/dados")
public class DadoController {

    private final Random random = new Random();

    @GetMapping("/{caras}")
    public ResultadoDado lanzarDado(@PathVariable int caras) {

        if (caras < 2) {
            throw new IllegalArgumentException(
                "El dado debe tener al menos 2 caras"
            );
        }

        int resultado = random.nextInt(caras) + 1;

        return new ResultadoDado(resultado);
    }

    public record ResultadoDado(int resultado) {
    }
}