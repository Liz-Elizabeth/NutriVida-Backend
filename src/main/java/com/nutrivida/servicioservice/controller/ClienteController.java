
package com.nutrivida.servicioservice.controller;

import com.nutrivida.servicioservice.model.Cliente;
import com.nutrivida.servicioservice.service.ClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // READ: listar todos los clientes
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    // READ: buscar cliente por ID
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    // CREATE: registrar un cliente
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.crear(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    // UPDATE: modificar un cliente
    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {

        return clienteService.actualizar(id, cliente);
    }

    // DELETE: eliminar un cliente
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
    }
}
