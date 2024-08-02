package com.prueba.cuenta.service;

import com.prueba.cuenta.dto.CuentaNuevaDTO;
import com.prueba.cuenta.model.Cuenta;
import java.math.BigDecimal;
import java.util.List;

public interface CuentaService {

  Cuenta crearCuenta(CuentaNuevaDTO cuentaNuevaDTO);

  Cuenta deshabilitarCuenta(String numeroCuenta);

  long eliminarCuenta(String numeroCuenta);

  Cuenta actualizarSaldoDisponible(String numeroCuenta, BigDecimal saldo);

  Cuenta obtenerCuentaPorNumero(String numeroCuenta);

  List<Cuenta> obtenerCuentasPorIdentificacion(String identificacion);
}
