package com.prueba.cuenta.mapper;

import com.prueba.cuenta.dto.CuentaNuevaDTO;
import com.prueba.cuenta.model.Cuenta;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CuentaMapper {

  public static Cuenta buildCuentaNueva(CuentaNuevaDTO dto) {
    return Cuenta.builder().numeroCuenta(dto.getNumeroCuenta()).tipoCuenta(dto.getTipoCuenta())
        .clienteIdentificacion(dto.getClienteIdentificacion()).saldoInicial(dto.getSaldoInicial())
        .build();
  }
  
}
