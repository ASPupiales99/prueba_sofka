package com.prueba.cuenta.mapper;

import com.prueba.cuenta.dto.ReporteDTO;
import com.prueba.cuenta.model.EstadoEnum;
import com.prueba.cuenta.model.Movimiento;
import com.prueba.cuenta.model.TipoCuentaEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReporteMapper {

  public static ReporteDTO buildReporte(Movimiento movimiento, String cliente) {
    return ReporteDTO.builder().cliente(cliente)
        .estado(EstadoEnum.findByValor(movimiento.getCuenta().getEstado()))
        .fecha(movimiento.getFecha()).numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
        .tipoCuenta(
            TipoCuentaEnum.findByValor(movimiento.getCuenta().getTipoCuenta()))
        .saldoInicial(movimiento.getCuenta().getSaldoInicial())
        .movimiento(movimiento.getValor())
        .saldoDisponible(movimiento.getSaldo()).build();
  }
}
