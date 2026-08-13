package com.inti.library.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.library.models.Miembro;
import com.inti.library.services.MiembroService;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {
    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @GetMapping
    public List<Miembro> obtenerTodos() {
        return miembroService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Miembro obtenerPorId(@PathVariable Long id) {
        return miembroService.obtenerPorId(id);
    }

    @PostMapping
    public Miembro guardarMiembro(@RequestBody Miembro miembro) {
        return miembroService.guardarMiembro(miembro);
    }

    @DeleteMapping("/{id}")
    public void eliminarMiembro(@PathVariable Long id) {
        miembroService.eliminarMiembro(id);
    }

    @PutMapping("/{id}")
    public Miembro actualizarMiembro(@PathVariable Long id, @RequestBody Miembro detallesMiembro) {
        return miembroService.actualizarMiembro(id, detallesMiembro);
    }

    @PatchMapping("/{id}")
    public Miembro actualizarParcialMiembro(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        return miembroService.actualizarParcialMiembro(id, campos);
    }
}
