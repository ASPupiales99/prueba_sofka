package com.prueba.persona.service;

import com.prueba.persona.dto.ClienteActualizarDTO;
import com.prueba.persona.dto.ClienteDTO;
import com.prueba.persona.dto.ClienteNuevoDTO;
import com.prueba.persona.model.Cliente;
import java.util.List;

public interface ClienteService {

  Cliente crearCliente(ClienteNuevoDTO clienteDTO);

  Cliente actualizarCliente(String identificacion, ClienteActualizarDTO clienteDTO);

  ClienteDTO obtenerClientePorIdentificacion(String identificacion);

  List<ClienteDTO> obtenerClientes();

  void eliminarCliente(String identificacion);
}
