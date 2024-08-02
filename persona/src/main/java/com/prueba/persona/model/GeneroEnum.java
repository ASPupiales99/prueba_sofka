package com.prueba.persona.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum GeneroEnum {
  MASCULINO("M", "Masculino"),
  FEMENINO("F", "Femenino"),
  OTRO("O", "OTRO");

  private final String valor;

  private final String texto;

  public static String findByValor(String valor) {
    for (GeneroEnum g : GeneroEnum.values()) {
      if (valor.compareTo(g.valor) == 0) {
        return g.texto;
      }
    }
    return null;
  }
}
