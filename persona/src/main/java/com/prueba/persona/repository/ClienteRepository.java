package com.prueba.persona.repository;

import com.prueba.persona.model.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

  Optional<Cliente> findByIdentificacion(String identificacion);

  @Transactional
  void deleteByIdentificacion(String identificacion);
}
