package com.prueba.cuenta.controller;

import com.prueba.cuenta.dto.CuentaNuevaDTO;
import com.prueba.cuenta.model.Cuenta;
import com.prueba.cuenta.service.CuentaService;
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
@RequestMapping(path = "/cuentas")
@RequiredArgsConstructor
@Slf4j
public class CuentaController {

  private final CuentaService service;

  @PostMapping
  public ResponseEntity<Cuenta> crearCuenta(@RequestBody CuentaNuevaDTO cuentaNuevaDTO) {
    try {
      Cuenta cuenta = service.crearCuenta(cuentaNuevaDTO);
      return ResponseEntity.ok(cuenta);
    } catch (Exception e) {
      log.error("Error al crear la cuenta: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping(path = "/{numero}")
  public ResponseEntity<Cuenta> deshabilitarCuenta(@PathVariable String numero) {
    try {
      Cuenta cuenta = service.deshabilitarCuenta(numero);
      return ResponseEntity.ok(cuenta);
    } catch (Exception e) {
      log.error("Error al deshabilitar la cuenta: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping(path = "/{identificacion}")
  public ResponseEntity<List<Cuenta>> obtenerCuentasPorIdentificacion(
      @PathVariable String identificacion) {
    try {
      List<Cuenta> cuentas = service.obtenerCuentasPorIdentificacion(identificacion);
      return ResponseEntity.ok(cuentas);
    } catch (Exception e) {
      log.error("Error al obtener el cliente: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping(path = "/{numero}")
  public ResponseEntity<String> eliminarCuentaPorNumero(
      @PathVariable String numero) {
    try {
      long cuentasEliminadas = service.eliminarCuenta(numero);
      return ResponseEntity.ok("Cuentas eliminada correctamente: " + cuentasEliminadas);
    } catch (Exception e) {
      log.error("Error al eliminar la cuenta: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
