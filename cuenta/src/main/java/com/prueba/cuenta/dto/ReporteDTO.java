package com.prueba.cuenta.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteDTO {

  private Date fecha;
  private String cliente;
  private String numeroCuenta;
  private String tipoCuenta;
  private BigDecimal saldoInicial;
  private String estado;
  private BigDecimal movimiento;
  private BigDecimal saldoDisponible;
}
