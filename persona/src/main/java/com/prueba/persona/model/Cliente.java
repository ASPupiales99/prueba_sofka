package com.prueba.persona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente extends Persona implements Serializable {

  @Column(name = "clave", nullable = false, length = 16)
  private String clave;
  @Column(name = "estado", nullable = false, length = 1)
  private String estado;
}
