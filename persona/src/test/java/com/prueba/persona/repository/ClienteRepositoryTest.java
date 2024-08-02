package com.prueba.persona.repository;

import com.prueba.persona.model.Cliente;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteRepositoryTest {

  @Autowired
  private ClienteRepository repository;

  @Test
  @DisplayName("Test 1: Crear cliente")
  @Order(1)
  @Rollback(value = false)
  void crearCliente() {
    //Action
    Cliente cliente = new Cliente();
    cliente.setIdentificacion("12345");
    cliente.setNombre("Andrew");
    cliente.setGenero("M");
    cliente.setEstado("A");
    cliente.setEdad(25);
    cliente.setDireccion("El Inca");
    cliente.setTelefono("0999999");
    cliente.setClave("hola");
    repository.save(cliente);

    //Verify
    System.out.println(cliente);
    Assertions.assertThat(cliente.getClave()).isEqualTo("hola");
  }

  @Test
  @Order(2)
  void findByIdentificacion() {
    //Action
    Cliente cliente = repository.findByIdentificacion("12345").get();

    //Verify
    System.out.println(cliente);
    Assertions.assertThat(cliente.getIdentificacion()).isEqualTo("12345");
  }

  @Test
  @Order(3)
  @Rollback(value = false)
  void deleteByIdentificacion() {
    //Action
    repository.deleteByIdentificacion("12345");
    Optional<Cliente> cliente = repository.findByIdentificacion("12345");

    //Verify
    Assertions.assertThat(cliente).isEmpty();
  }
}