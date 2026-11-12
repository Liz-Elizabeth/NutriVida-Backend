
package com.nutrivida.servicioservice.service;

import com.nutrivida.servicioservice.model.Servicio;
import com.nutrivida.servicioservice.model.Venta;
import com.nutrivida.servicioservice.repository.ClienteRepository;
import com.nutrivida.servicioservice.repository.ServicioRepository;
import com.nutrivida.servicioservice.repository.VentaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ServicioRepository servicioRepository;

    // READ: listar todas las ventas
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    // READ: buscar una venta por ID
    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Venta no encontrada"
                ));
    }

    // CREATE: registrar una venta
    public Venta crear(Venta venta) {
        venta.setId(null);

        BigDecimal total = calcularTotal(
                venta.getClienteId(),
                venta.getServicioId(),
                venta.getCantidad()
        );

        venta.setTotal(total);

        return ventaRepository.save(venta);
    }

    // UPDATE: actualizar una venta
    public Venta actualizar(Long id, Venta datos) {
        Venta actual = buscarPorId(id);

        BigDecimal total = calcularTotal(
                datos.getClienteId(),
                datos.getServicioId(),
                datos.getCantidad()
        );

        actual.setClienteId(datos.getClienteId());
        actual.setServicioId(datos.getServicioId());
        actual.setCantidad(datos.getCantidad());
        actual.setTotal(total);

        return ventaRepository.save(actual);
    }

    // DELETE: eliminar una venta
    public void eliminar(Long id) {
        Venta venta = buscarPorId(id);
        ventaRepository.delete(venta);
    }

    // Validar los datos y calcular el total
    private BigDecimal calcularTotal(
            Long clienteId,
            Long servicioId,
            Integer cantidad) {

        if (clienteId == null || servicioId == null
                || cantidad == null || cantidad <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cliente, servicio y cantidad positiva son obligatorios"
            );
        }

        if (!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente no encontrado"
            );
        }

        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Servicio no encontrado"
                ));

        return servicio.getPrecio()
                .multiply(BigDecimal.valueOf(cantidad));
    }
}
