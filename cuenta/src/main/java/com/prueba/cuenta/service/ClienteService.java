package com.prueba.cuenta.service;

import com.prueba.cuenta.client.ClienteClient;
import com.prueba.cuenta.model.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

  private final ClienteClient client;

  public ClienteDTO obtenerClientePorIdentificacion(String identificacion) {
    return client.obtenerClientePorIdentificacion(identificacion);
  }
}
