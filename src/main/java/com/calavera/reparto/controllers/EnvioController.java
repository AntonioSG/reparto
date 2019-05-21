/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calavera.reparto.controllers;

/**
 *
 * @author Antonio
 */


import com.calavera.reparto.model.Envio;
import com.calavera.reparto.repositories.EnvioRepository;
import com.calavera.reparto.exceptions.EnvioNotFoundException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EnvioController {

  private final EnvioRepository repository;

  EnvioController(EnvioRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/envio")
  Iterable<Envio> all() {
    return repository.findAll();
  }

  @PostMapping("/envio")
  Envio newEnvio(@RequestBody Envio newEnvio) {
    return repository.save(newEnvio);
  }

  // Single item

  @GetMapping("/envio/{id}")
  Envio one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new EnvioNotFoundException(id));
  }

  @PutMapping("/employees/{id}")
  Envio replaceEmployee(@RequestBody Envio newEnvio, @PathVariable Long id) {

    return repository.findById(id)
      .map(envio -> {
        envio.setCliente(newEnvio.getCliente());
        envio.setDireccionOrigen(newEnvio.getDireccionOrigen());
        return repository.save(envio);
      })
      .orElseGet(() -> {
        newEnvio.setId(id);
        return repository.save(newEnvio);
      });
  }

  @DeleteMapping("/envio/{id}")
  void deleteEnvio(@PathVariable Long id) {
    repository.deleteById(id);
  }
}