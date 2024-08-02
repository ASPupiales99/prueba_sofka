package com.prueba.cuenta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuenta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cuenta_id")
  private long cuentaId;
  @Column(name = "numero_cuenta", nullable = false, length = 20)
  private String numeroCuenta;
  @Column(name = "tipo_cuenta", nullable = false, length = 1)
  private String tipoCuenta;
  @Column(name = "saldo_inicial", nullable = false)
  private BigDecimal saldoInicial;
  @Column(name = "estado", nullable = false, length = 1)
  private String estado;
  @Column(name = "cliente_identificacion", nullable = false, length = 13)
  private String clienteIdentificacion;
  @Column(name = "saldo_disponible", nullable = false)
  private BigDecimal saldoDisponible;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Movimiento> movimientos;
}
