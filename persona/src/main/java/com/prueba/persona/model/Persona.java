package com.prueba.persona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Persona {

  @Id
  @Column(name = "identificacion", length = 13)
  private String identificacion;
  @Column(name = "nombre", nullable = false, length = 150)
  private String nombre;
  @Column(name = "genero", nullable = false, length = 1)
  private String genero;
  @Column(name = "edad", nullable = false)
  private Integer edad;
  @Column(name = "direccion")
  private String direccion;
  @Column(name = "telefono", length = 10)
  private String telefono;

}
