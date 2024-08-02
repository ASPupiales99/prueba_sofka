package com.prueba.cuenta.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CuentaNuevaDTO {

  private String numeroCuenta;
  private String tipoCuenta;
  private BigDecimal saldoInicial;
  private String clienteIdentificacion;
}
