package com.prueba.persona.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.prueba.persona.dto.ClienteActualizarDTO;
import com.prueba.persona.dto.ClienteDTO;
import com.prueba.persona.dto.ClienteNuevoDTO;
import com.prueba.persona.model.Cliente;
import com.prueba.persona.repository.ClienteRepository;
import com.prueba.persona.service.impl.ClienteServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteServiceTest {

  @Mock
  private ClienteRepository repository;
  @InjectMocks
  private ClienteServiceImpl service;
  private Cliente cliente;
  private ClienteNuevoDTO clienteNuevoDTO;

  @BeforeEach
  void setUp() {
    cliente = new Cliente();
    cliente.setIdentificacion("12345");
    cliente.setNombre("Andrew");
    cliente.setGenero("M");
    cliente.setEstado("A");
    cliente.setEdad(25);
    cliente.setDireccion("El Inca");
    cliente.setTelefono("0999999");
    cliente.setClave("hola");

    clienteNuevoDTO = ClienteNuevoDTO.builder().identificacion("12345").nombre("Andrew").genero("M")
        .edad(25)
        .direccion("El Inca").telefono("0999999").clave("hola").build();
  }

  @Test
  @Order(1)
  void crearCliente() {
    //precondition
    given(repository.save(cliente)).willReturn(cliente);

    //action
    Cliente clienteCreado = service.crearCliente(clienteNuevoDTO);

    //verify
    System.out.println(clienteCreado);
    assertThat(clienteCreado).isNotNull();
  }

  @Test
  @Order(4)
  void actualizarCliente() {
    //precondition
    given(repository.findByIdentificacion("12345")).willReturn(Optional.of(cliente));
    cliente.setClave("234");
    cliente.setNombre("Sebas");
    given(repository.save(cliente)).willReturn(cliente);

    ClienteActualizarDTO dto = new ClienteActualizarDTO();
    dto.setNombre("Sebas");
    dto.setGenero("M");
    dto.setEstado("A");
    dto.setEdad(25);
    dto.setDireccion("El Inca");
    dto.setTelefono("0999999");
    dto.setClave("234");

    //action
    Cliente clienteActualizado = service.actualizarCliente("12345", dto);

    //verify
    System.out.println(clienteActualizado);
    assertThat(clienteActualizado.getNombre()).isEqualTo("Sebas");
    assertThat(clienteActualizado.getClave()).isEqualTo("234");
  }

  @Test
  @Order(2)
  void obtenerClientePorIdentificacion() {
    //precondition
    given(repository.findByIdentificacion("12345")).willReturn(Optional.of(cliente));

    //action
    ClienteDTO clienteBuscado = service.obtenerClientePorIdentificacion("12345");

    //verify
    System.out.println(clienteBuscado);
    assertThat(clienteBuscado).isNotNull();
  }

  @Test
  @Order(3)
  void obtenerClientes() {
    //precondition
    given(repository.findAll()).willReturn(List.of(cliente));

    //action
    List<ClienteDTO> clientes = service.obtenerClientes();

    //verify
    System.out.println(clientes);
    assertThat(clientes).isNotNull();
    assertThat(clientes.size()).isEqualTo(1);
  }

  @Test
  @Order(5)
  void eliminarCliente() {
    //precondition
    given(repository.findByIdentificacion("12345")).willReturn(Optional.of(cliente));
    willDoNothing().given(repository).deleteByIdentificacion(cliente.getIdentificacion());

    //action
    service.eliminarCliente(cliente.getIdentificacion());

    //verify
    verify(repository, times(1)).deleteByIdentificacion(cliente.getIdentificacion());
  }
}