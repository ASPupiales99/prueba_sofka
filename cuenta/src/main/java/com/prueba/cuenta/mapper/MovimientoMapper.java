package com.prueba.cuenta.mapper;

import com.prueba.cuenta.dto.MovimientoDTO;
import com.prueba.cuenta.model.Movimiento;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovimientoMapper {

  public static Movimiento buildMovimiento(MovimientoDTO dto) {
    return Movimiento.builder().tipoMovimiento(dto.getTipoMovimiento()).fecha(dto.getFecha())
        .valor(dto.getValor()).build();
  }
}
