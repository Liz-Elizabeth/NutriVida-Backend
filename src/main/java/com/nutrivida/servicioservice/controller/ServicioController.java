

package com.nutrivida.servicioservice.controller;

import com.nutrivida.servicioservice.model.Servicio;
import com.nutrivida.servicioservice.service.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;

    // READ: obtener todos los servicios
    @GetMapping
    public List<Servicio> listar() {
        return servicioService.listar();
    }

    // READ: obtener un servicio por su ID
    @GetMapping("/{id}")
    public Servicio buscarPorId(@PathVariable Long id) {
        return servicioService.buscarPorId(id);
    }

    // CREATE: registrar un nuevo servicio
    @PostMapping
    public ResponseEntity<Servicio> crear(@RequestBody Servicio servicio) {
        Servicio nuevo = servicioService.crear(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // UPDATE: modificar un servicio existente
    @PutMapping("/{id}")
    public Servicio actualizar(
            @PathVariable Long id,
            @RequestBody Servicio servicio) {

        return servicioService.actualizar(id, servicio);
    }

    // DELETE: eliminar un servicio
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
    }
}
