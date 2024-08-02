package com.prueba.persona.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {

  private String identificacion;
  private String nombre;
  private String genero;
  private Integer edad;
  private String direccion;
  private String telefono;
  private String clave;
  private String estado;
}
