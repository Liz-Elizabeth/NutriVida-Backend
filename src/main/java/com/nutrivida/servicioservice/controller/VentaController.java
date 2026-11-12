
package com.nutrivida.servicioservice.controller;

import com.nutrivida.servicioservice.model.Venta;
import com.nutrivida.servicioservice.service.VentaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    // READ: obtener todas las ventas
    @GetMapping
    public List<Venta> listar() {
        return ventaService.listar();
    }

    // READ: buscar una venta por ID
    @GetMapping("/{id}")
    public Venta buscarPorId(@PathVariable Long id) {
        return ventaService.buscarPorId(id);
    }

    // CREATE: registrar una venta
    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody Venta venta) {
        Venta nueva = ventaService.crear(venta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva);
    }

    // UPDATE: modificar una venta
    @PutMapping("/{id}")
    public Venta actualizar(
            @PathVariable Long id,
            @RequestBody Venta venta) {

        return ventaService.actualizar(id, venta);
    }

    // DELETE: eliminar una venta
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
    }
}
