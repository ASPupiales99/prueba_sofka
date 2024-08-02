package com.prueba.cuenta.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TipoCuentaEnum {
  AHORROS("A", "Ahorros"),
  CORRIENTE("C", "Corriente");

  private final String valor;
  private final String texto;

  public static String findByValor(String valor) {
    for (TipoCuentaEnum tc : TipoCuentaEnum.values()) {
      if (valor.compareTo(tc.valor) == 0) {
        return tc.texto;
      }
    }
    return null;
  }
}
