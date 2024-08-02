package com.prueba.cuenta.client;

import com.prueba.cuenta.model.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "persona", url = "localhost:8080/clientes")
public interface ClienteClient {

  @GetMapping(path = "/{identificacion}")
  ClienteDTO obtenerClientePorIdentificacion(@PathVariable String identificacion);
}
