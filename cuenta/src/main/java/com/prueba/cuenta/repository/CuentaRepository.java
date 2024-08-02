package com.prueba.cuenta.repository;

import com.prueba.cuenta.model.Cuenta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

  List<Cuenta> findByClienteIdentificacion(String identificacion);

  Optional<Cuenta> findByClienteIdentificacionAndTipoCuenta(String identificacion, String tipo);

  Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

  @Transactional
  long deleteByNumeroCuenta(String numeroCuenta);

}
