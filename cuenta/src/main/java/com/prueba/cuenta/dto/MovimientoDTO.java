package com.prueba.cuenta.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class MovimientoDTO {

  private Date fecha;
  private String tipoMovimiento;
  private BigDecimal valor;
}
