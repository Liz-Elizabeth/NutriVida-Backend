
package com.nutrivida.servicioservice.service;

import com.nutrivida.servicioservice.model.Cliente;
import com.nutrivida.servicioservice.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    // READ: listar todos los clientes
    public List<Cliente> listar() {
        return repository.findAll();
    }

    // READ: buscar un cliente por ID
    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente no encontrado"
                ));
    }

    // CREATE: registrar un cliente
    public Cliente crear(Cliente cliente) {
        validar(cliente);
        cliente.setId(null);
        return repository.save(cliente);
    }

    // UPDATE: modificar un cliente
    public Cliente actualizar(Long id, Cliente datos) {
        Cliente actual = buscarPorId(id);

        validar(datos);

        actual.setRun(datos.getRun());
        actual.setPnombre(datos.getPnombre());
        actual.setSnombre(datos.getSnombre());
        actual.setApaterno(datos.getApaterno());
        actual.setAmaterno(datos.getAmaterno());
        actual.setCorreo(datos.getCorreo());
        actual.setFechaNacimiento(datos.getFechaNacimiento());
        actual.setDireccion(datos.getDireccion());

        return repository.save(actual);
    }

    // DELETE: eliminar un cliente
    public void eliminar(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }

    // Validar datos obligatorios
    private void validar(Cliente cliente) {
        if (cliente.getRun() == null
                || cliente.getRun().isBlank()
                || cliente.getPnombre() == null
                || cliente.getPnombre().isBlank()
                || cliente.getApaterno() == null
                || cliente.getApaterno().isBlank()
                || cliente.getCorreo() == null
                || cliente.getCorreo().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "RUN, primer nombre, apellido paterno y correo son obligatorios"
            );
        }
    }
}
