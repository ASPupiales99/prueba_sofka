package com.prueba.cuenta.service.impl;

import com.prueba.cuenta.dto.MovimientoDTO;
import com.prueba.cuenta.dto.ReporteDTO;
import com.prueba.cuenta.mapper.MovimientoMapper;
import com.prueba.cuenta.mapper.ReporteMapper;
import com.prueba.cuenta.model.ClienteDTO;
import com.prueba.cuenta.model.Cuenta;
import com.prueba.cuenta.model.EstadoEnum;
import com.prueba.cuenta.model.Movimiento;
import com.prueba.cuenta.model.TipoMovimientoEnum;
import com.prueba.cuenta.repository.MovimientoRepository;
import com.prueba.cuenta.service.ClienteService;
import com.prueba.cuenta.service.CuentaService;
import com.prueba.cuenta.service.MovimientoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

  private final CuentaService cuentaService;
  private final MovimientoRepository repository;
  private final ClienteService clienteService;

  @Override
  public Movimiento crearMovimiento(String numeroCuenta, MovimientoDTO movimientoDTO) {
    BigDecimal saldo = BigDecimal.ZERO;

    Cuenta cuenta = cuentaService.obtenerCuentaPorNumero(numeroCuenta);

    if (cuenta != null) {
      if (cuenta.getEstado().compareTo(EstadoEnum.INACTIVO.getValor()) == 0) {
        throw new RuntimeException("El numero de cuenta " + numeroCuenta
            + " se encuentra inactivo, no puede realizar movimientos");
      }

      Movimiento movimiento = MovimientoMapper.buildMovimiento(movimientoDTO);

      if (cuenta.getSaldoDisponible().compareTo(BigDecimal.ZERO) == 0
          && movimiento.getTipoMovimiento().compareTo(
          TipoMovimientoEnum.RETIRO.getValor()) == 0) {
        throw new RuntimeException(
            "Saldo insuficiente para realizar movimiento en la cuenta " + numeroCuenta);
      }

      saldo = cuenta.getSaldoDisponible().add(movimiento.getValor());
      if (saldo.compareTo(BigDecimal.ZERO) < 0) {
        throw new RuntimeException(
            "Saldo insuficiente para realizar movimiento en la cuenta " + numeroCuenta);
      }

      cuenta.setSaldoDisponible(saldo);
      movimiento.setCuenta(cuenta);
      movimiento.setSaldo(saldo);
      repository.save(movimiento);
      cuentaService.actualizarSaldoDisponible(numeroCuenta, saldo);

      return movimiento;
    }
    return null;
  }

  @Override
  public List<Movimiento> obtenerMovimientosRangoFecha(String identificacion, Date fechaInicio,
      Date fechaFin) {
    List<Movimiento> movimientos;
    ClienteDTO cliente = clienteService.obtenerClientePorIdentificacion(identificacion);
    if (cliente == null) {
      throw new RuntimeException(
          "No existe un cliente con identificacion " + identificacion);
    }
    movimientos = repository.findByCuentaClienteIdentificacionAndFechaBetween(identificacion,
        fechaInicio, fechaFin);
    return movimientos;
  }

  @Override
  public List<ReporteDTO> generarReporteMovimientosRangoFecha(String identificacion,
      Date fechaInicio, Date fechaFin) {
    List<Movimiento> movimientos;
    List<ReporteDTO> reportes = new ArrayList<>();
    movimientos = obtenerMovimientosRangoFecha(identificacion, fechaInicio, fechaFin);
    if (movimientos.isEmpty()) {
      throw new RuntimeException(
          "No existen datos para los parametros proporcionados.");
    }
    ClienteDTO cliente = clienteService.obtenerClientePorIdentificacion(identificacion);
    for (Movimiento m : movimientos) {
      reportes.add(ReporteMapper.buildReporte(m, cliente.getNombre()));
    }
    return reportes;
  }
}
