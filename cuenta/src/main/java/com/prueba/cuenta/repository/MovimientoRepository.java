package com.prueba.cuenta.repository;

import com.prueba.cuenta.model.Movimiento;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

  List<Movimiento> findByCuentaClienteIdentificacionAndFechaBetween(String identificacion,
      Date fechaInicio, Date fechaFin);
}
