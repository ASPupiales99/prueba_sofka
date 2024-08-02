package com.prueba.persona.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EstadoEnum {
  ACTIVO("A", "Activo"),
  INACTIVO("I", "Inactivo");

  private final String valor;
  private final String texto;

  public static String findByValor(String valor) {
    for (EstadoEnum e : EstadoEnum.values()) {
      if (valor.compareTo(e.valor) == 0) {
        return e.texto;
      }
    }
    return null;
  }
}
