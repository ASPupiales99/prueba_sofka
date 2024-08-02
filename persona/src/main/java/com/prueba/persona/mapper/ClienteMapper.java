package com.prueba.persona.mapper;

import com.prueba.persona.dto.ClienteActualizarDTO;
import com.prueba.persona.dto.ClienteDTO;
import com.prueba.persona.dto.ClienteNuevoDTO;
import com.prueba.persona.model.Cliente;
import com.prueba.persona.model.EstadoEnum;
import com.prueba.persona.model.GeneroEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

  public static Cliente buildClienteNuevo(ClienteNuevoDTO dto) {
    Cliente cliente = new Cliente();
    cliente.setIdentificacion(dto.getIdentificacion());
    cliente.setNombre(dto.getNombre());
    cliente.setGenero(dto.getGenero());
    cliente.setEdad(dto.getEdad());
    cliente.setDireccion(dto.getDireccion());
    cliente.setTelefono(dto.getTelefono());
    cliente.setClave(dto.getClave());
    return cliente;
  }

  public static Cliente buildClienteActualizar(Cliente cliente, ClienteActualizarDTO dto) {
    cliente.setNombre(dto.getNombre());
    cliente.setGenero(dto.getGenero());
    cliente.setEdad(dto.getEdad());
    cliente.setDireccion(dto.getDireccion());
    cliente.setTelefono(dto.getTelefono());
    cliente.setClave(dto.getClave());
    return cliente;
  }

  public static ClienteDTO buildClienteDto(Cliente cliente) {
    return ClienteDTO.builder().identificacion(cliente.getIdentificacion()).nombre(
            cliente.getNombre()).clave(cliente.getClave()).edad(cliente.getEdad())
        .direccion(cliente.getDireccion()).genero(GeneroEnum.findByValor(cliente.getGenero()))
        .estado(EstadoEnum.findByValor(cliente.getEstado())).telefono(cliente.getTelefono())
        .build();
  }

  public static List<ClienteDTO> buildClientesDto(List<Cliente> clientes) {
    List<ClienteDTO> clientesDto = new ArrayList<>();
    for (Cliente c : clientes) {
      clientesDto.add(buildClienteDto(c));
    }
    return clientesDto;
  }
}
