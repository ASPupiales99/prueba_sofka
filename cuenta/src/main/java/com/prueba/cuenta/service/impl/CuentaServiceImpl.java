package com.prueba.cuenta.service.impl;

import com.prueba.cuenta.dto.CuentaNuevaDTO;
import com.prueba.cuenta.mapper.CuentaMapper;
import com.prueba.cuenta.model.Cuenta;
import com.prueba.cuenta.model.EstadoEnum;
import com.prueba.cuenta.repository.CuentaRepository;
import com.prueba.cuenta.service.CuentaService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

  private final CuentaRepository repository;

  @Override
  public Cuenta crearCuenta(CuentaNuevaDTO cuentaNuevaDTO) {
    Optional<Cuenta> cuentaDb = repository.findByNumeroCuenta(cuentaNuevaDTO.getNumeroCuenta());
    if (cuentaDb.isPresent()) {
      throw new RuntimeException("El numero de cuenta " + cuentaNuevaDTO.getNumeroCuenta()
          + " ya se encuentra registrado");
    }
    Cuenta cuenta = CuentaMapper.buildCuentaNueva(cuentaNuevaDTO);
    cuenta.setEstado(EstadoEnum.ACTIVO.getValor());
    cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
    repository.save(cuenta);
    return cuenta;
  }

  @Override
  public Cuenta deshabilitarCuenta(String numeroCuenta) {
    Optional<Cuenta> cuentaDb = repository.findByNumeroCuenta(numeroCuenta);
    if (cuentaDb.isEmpty()) {
      throw new RuntimeException("El numero de cuenta " + numeroCuenta
          + " no se encuentra registrado");
    }
    Cuenta cuenta = cuentaDb.get();
    cuenta.setEstado(EstadoEnum.INACTIVO.getValor());
    repository.save(cuenta);
    return cuenta;
  }

  @Override
  public long eliminarCuenta(String numeroCuenta) {
    Optional<Cuenta> cuentaDb = repository.findByNumeroCuenta(numeroCuenta);
    if (cuentaDb.isEmpty()) {
      throw new RuntimeException("El numero de cuenta " + numeroCuenta
          + " no se encuentra registrado");
    }
    long cuentasEliminadas = repository.deleteByNumeroCuenta(numeroCuenta);
    return cuentasEliminadas;
  }

  @Override
  public Cuenta actualizarSaldoDisponible(String numeroCuenta, BigDecimal saldo) {
    Optional<Cuenta> cuentaDb = repository.findByNumeroCuenta(numeroCuenta);
    if (cuentaDb.isEmpty()) {
      throw new RuntimeException("El numero de cuenta " + numeroCuenta
          + " no se encuentra registrado");
    }
    Cuenta cuenta = cuentaDb.get();
    cuenta.setSaldoDisponible(saldo);
    repository.save(cuenta);
    return cuenta;
  }

  @Override
  public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
    Optional<Cuenta> cuentaDb = repository.findByNumeroCuenta(numeroCuenta);
    if (cuentaDb.isEmpty()) {
      throw new RuntimeException("El numero de cuenta " + numeroCuenta
          + " no se encuentra registrado");
    }
    return cuentaDb.orElse(null);
  }

  @Override
  public List<Cuenta> obtenerCuentasPorIdentificacion(String identificacion) {
    return repository.findByClienteIdentificacion(identificacion);
  }
}
