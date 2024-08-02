package com.prueba.persona.dto;

import lombok.Data;

@Data
public class ClienteActualizarDTO {

  private String nombre;
  private String genero;
  private Integer edad;
  private String direccion;
  private String telefono;
  private String clave;
  private String estado;
}
