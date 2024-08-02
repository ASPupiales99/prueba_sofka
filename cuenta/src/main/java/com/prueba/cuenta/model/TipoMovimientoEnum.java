package com.prueba.cuenta.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TipoMovimientoEnum {
  RETIRO("R", "Retiro de"),
  DEPOSITO("C", "Deposito de");

  private final String valor;
  private final String texto;

  public static String findByValor(String valor) {
    for (TipoMovimientoEnum tm : TipoMovimientoEnum.values()) {
      if (valor.compareTo(tm.valor) == 0) {
        return tm.texto;
      }
    }
    return null;
  }
}
