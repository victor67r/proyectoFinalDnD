package com.proyectofinaldnd.backend.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DadoControllerTest {

    private final DadoController controller = new DadoController();

    @Test
    void resultadoEstaDentroDelRangoDelDado() {
        int[] carasPosibles = {2, 6, 20};

        for (int caras : carasPosibles) {
            for (int intento = 0; intento < 100; intento++) {
                int resultado = controller.lanzarDado(caras).resultado();

                assertTrue(resultado >= 1 && resultado <= caras,
                        "El resultado debe estar entre 1 y el número de caras");
            }
        }
    }

    @Test
    void rechazaDadosConMenosDeDosCaras() {
        int[] valoresInvalidos = {1, 0, -5};

        for (int caras : valoresInvalidos) {
            assertThrows(IllegalArgumentException.class,
                    () -> controller.lanzarDado(caras));
        }
    }
}