
package com.nutrivida.servicioservice.service;

import com.nutrivida.servicioservice.model.Servicio;
import com.nutrivida.servicioservice.repository.ServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioService {

    private final ServicioRepository repository;

    // READ: listar todos los servicios
    public List<Servicio> listar() {
        return repository.findAll();
    }

    // READ: buscar un servicio por su ID
    public Servicio buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Servicio no encontrado"
                ));
    }

    // CREATE: crear un servicio
    public Servicio crear(Servicio servicio) {
        validar(servicio);
        servicio.setId(null);

        if (servicio.getActivo() == null) {
            servicio.setActivo(true);
        }

        return repository.save(servicio);
    }

    // UPDATE: modificar un servicio
    public Servicio actualizar(Long id, Servicio datos) {
        Servicio actual = buscarPorId(id);

        validar(datos);

        actual.setNombre(datos.getNombre());
        actual.setDescripcion(datos.getDescripcion());
        actual.setPrecio(datos.getPrecio());
        actual.setImagenUrl(datos.getImagenUrl());

        if (datos.getActivo() != null) {
            actual.setActivo(datos.getActivo());
        }

        return repository.save(actual);
    }

    // DELETE: eliminar un servicio
    public void eliminar(Long id) {
        Servicio servicio = buscarPorId(id);
        repository.delete(servicio);
    }

    // Comprobar los datos antes de guardarlos
    private void validar(Servicio servicio) {
        if (servicio.getNombre() == null
                || servicio.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre es obligatorio"
            );
        }

        if (servicio.getPrecio() == null
                || servicio.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El precio debe ser mayor que cero"
            );
        }
    }
}
