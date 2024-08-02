package com.prueba.persona.controller;

import com.prueba.persona.dto.ClienteActualizarDTO;
import com.prueba.persona.dto.ClienteDTO;
import com.prueba.persona.dto.ClienteNuevoDTO;
import com.prueba.persona.model.Cliente;
import com.prueba.persona.service.ClienteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

  private final ClienteService service;

  @PostMapping
  public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteNuevoDTO clienteNuevoDTO) {
    try {
      Cliente cliente = service.crearCliente(clienteNuevoDTO);
      return ResponseEntity.ok(cliente);
    } catch (Exception e) {
      log.error("Error al crear el cliente: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping(path = "/{identificacion}")
  public ResponseEntity<Cliente> actualizarCliente(@PathVariable String identificacion,
      @RequestBody ClienteActualizarDTO clienteActualizarDTO) {
    try {
      Cliente cliente = service.actualizarCliente(identificacion, clienteActualizarDTO);
      return ResponseEntity.ok(cliente);
    } catch (Exception e) {
      log.error("Error al actualizar el cliente: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping(path = "/{identificacion}")
  public ResponseEntity<ClienteDTO> obtenerClientePorIdentificacion(
      @PathVariable String identificacion) {
    try {
      ClienteDTO cliente = service.obtenerClientePorIdentificacion(identificacion);
      return ResponseEntity.ok(cliente);
    } catch (Exception e) {
      log.error("Error al obtener el cliente: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
    try {
      return ResponseEntity.ok(service.obtenerClientes());
    } catch (Exception e) {
      log.error("Error al obtener clientes: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping(path = "/{identificacion}")
  public ResponseEntity<String> eliminarClientePorIdentificacion(
      @PathVariable String identificacion) {
    try {
      service.eliminarCliente(identificacion);
      return ResponseEntity.ok("Cliente eliminado correctamente");
    } catch (Exception e) {
      log.error("Error al eliminar el cliente: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
