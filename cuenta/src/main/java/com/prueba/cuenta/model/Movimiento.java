package com.prueba.cuenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimiento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long movimientoId;
  @Temporal(TemporalType.DATE)
  @Column(name = "fecha", nullable = false)
  private Date fecha;
  @Column(name = "tipo_movimiento", nullable = false, length = 1)
  private String tipoMovimiento;
  @Column(name = "valor", nullable = false)
  private BigDecimal valor;
  @Column(name = "saldo", nullable = false)
  private BigDecimal saldo;

  @JoinColumn(name = "cuenta_id", referencedColumnName = "cuenta_id")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JsonIgnore
  private Cuenta cuenta;

}
