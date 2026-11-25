package com.proyectofinaldnd.backend.controller;

import com.proyectofinaldnd.backend.entity.Monstruo;
import com.proyectofinaldnd.backend.repository.MonstruoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monstruos")
@CrossOrigin(origins = "*")
public class MonstruoController {

    @Autowired
    private MonstruoRepository monstruoRepository;

    @GetMapping("/{id}")
    public Monstruo obtenerMonstruo(@PathVariable Long id) {
        return monstruoRepository.findById(id).orElse(null);
    }

    @PostMapping("/crear")
    public Monstruo crearMonstruo(@RequestBody Monstruo monstruo) {
        return monstruoRepository.save(monstruo);
    }
}
