package com.prueba.cuenta.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuentaDTO {

  private String numeroCuenta;
  private String tipoCuenta;
  private BigDecimal saldoInicial;
  private String estado;
  private String clienteIdentificacion;
  private BigDecimal saldoDisponible;
}
