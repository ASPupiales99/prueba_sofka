package com.prueba.cuenta.controller;

import com.prueba.cuenta.dto.MovimientoDTO;
import com.prueba.cuenta.dto.ReporteDTO;
import com.prueba.cuenta.model.Movimiento;
import com.prueba.cuenta.service.MovimientoService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/movimientos")
@RequiredArgsConstructor
@Slf4j
public class MovimientoController {

  private final MovimientoService service;

  @PostMapping(path = "/{numero}")
  public ResponseEntity<Movimiento> crearMovimiento(@PathVariable String numero,
      @RequestBody MovimientoDTO movimientoDTO) {
    try {
      Movimiento movimientoRealizado = service.crearMovimiento(numero, movimientoDTO);
      return ResponseEntity.ok(movimientoRealizado);
    } catch (Exception e) {
      log.error("Error al crear el movimiento: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping(path = "/{identificacion}/reportes")
  public ResponseEntity<List<ReporteDTO>> generarReporte(@PathVariable String identificacion,
      @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
      @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
    try {
      List<ReporteDTO> reportes = service.generarReporteMovimientosRangoFecha(identificacion,
          fechaInicio, fechaFin);
      return ResponseEntity.ok(reportes);
    } catch (Exception e) {
      log.error("Error al crear el reporte: {}", e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
