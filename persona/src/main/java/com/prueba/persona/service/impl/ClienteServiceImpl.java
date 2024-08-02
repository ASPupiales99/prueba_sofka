package com.prueba.persona.service.impl;

import com.prueba.persona.dto.ClienteActualizarDTO;
import com.prueba.persona.dto.ClienteDTO;
import com.prueba.persona.dto.ClienteNuevoDTO;
import com.prueba.persona.mapper.ClienteMapper;
import com.prueba.persona.model.Cliente;
import com.prueba.persona.model.EstadoEnum;
import com.prueba.persona.repository.ClienteRepository;
import com.prueba.persona.service.ClienteService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository repository;

  @Override
  public Cliente crearCliente(ClienteNuevoDTO clienteDTO) {
    Optional<Cliente> clienteDb = repository.findByIdentificacion(clienteDTO.getIdentificacion());
    if (clienteDb.isPresent()) {
      throw new RuntimeException("Cliente con identificacion " + clienteDTO.getIdentificacion()
          + " ya se encuentra registrado");
    }
    Cliente cliente = ClienteMapper.buildClienteNuevo(clienteDTO);
    cliente.setEstado(EstadoEnum.ACTIVO.getValor());
    repository.save(cliente);
    return cliente;
  }

  @Override
  public Cliente actualizarCliente(String identificacion, ClienteActualizarDTO clienteDTO) {
    Optional<Cliente> clienteDb = repository.findByIdentificacion(identificacion);
    if (clienteDb.isEmpty()) {
      throw new RuntimeException("Cliente con identificacion " + identificacion
          + " no se encuentra registrado");
    }
    Cliente cliente = ClienteMapper.buildClienteActualizar(clienteDb.get(), clienteDTO);
    repository.save(cliente);
    return cliente;
  }

  @Override
  public ClienteDTO obtenerClientePorIdentificacion(String identificacion) {
    Optional<Cliente> clienteDb = repository.findByIdentificacion(identificacion);
    if (clienteDb.isEmpty()) {
      throw new RuntimeException("Cliente con identificacion " + identificacion
          + " no se encuentra registrado");
    }
    return ClienteMapper.buildClienteDto(clienteDb.get());
  }

  @Override
  public List<ClienteDTO> obtenerClientes() {
    return ClienteMapper.buildClientesDto(repository.findAll());
  }

  @Override
  public void eliminarCliente(String identificacion) {
    Optional<Cliente> clienteDb = repository.findByIdentificacion(identificacion);
    if (clienteDb.isEmpty()) {
      throw new RuntimeException("Cliente con identificacion " + identificacion
          + " no se encuentra registrado");
    }
    repository.deleteByIdentificacion(identificacion);
  }
}
