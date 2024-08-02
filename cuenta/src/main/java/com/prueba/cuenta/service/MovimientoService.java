package com.prueba.cuenta.service;

import com.prueba.cuenta.dto.MovimientoDTO;
import com.prueba.cuenta.dto.ReporteDTO;
import com.prueba.cuenta.model.Movimiento;
import java.util.Date;
import java.util.List;

public interface MovimientoService {

  Movimiento crearMovimiento(String numeroCuenta, MovimientoDTO movimientoDTO);

  List<Movimiento> obtenerMovimientosRangoFecha(String identificacion, Date fechaInicio,
      Date fechaFin);

  List<ReporteDTO> generarReporteMovimientosRangoFecha(String identificacion, Date fechaInicio,
      Date fechaFin);
}
