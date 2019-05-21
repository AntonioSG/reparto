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
import com.calavera.reparto.model.Repartidor;
import com.calavera.reparto.repositories.RepartidorRepository;
import com.calavera.reparto.exceptions.RepartidorNotFoundException;
import com.calavera.reparto.model.Envio;
import com.calavera.reparto.model.Repartidor;
import com.calavera.reparto.repositories.EnvioRepository;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RepartidorController {

    private final RepartidorRepository repartidorRepo;
    private final EnvioRepository envioRepo;

    RepartidorController(RepartidorRepository rRepository, EnvioRepository eRepository) {
        this.repartidorRepo = rRepository;
        this.envioRepo = eRepository;
    }

    // Aggregate root
    @GetMapping("/repartidor")
    Iterable<Repartidor> all() {
        return repartidorRepo.findAll();
    }

    @PostMapping("/repartidor")
    Repartidor newRepartidor(@RequestBody Repartidor newRepartidor) {
        return repartidorRepo.save(newRepartidor);
    }

    // Single item
    @GetMapping("/repartidor/{id}")
    Repartidor one(@PathVariable Long id) {

        return repartidorRepo.findById(id)
                .orElseThrow(() -> new RepartidorNotFoundException(id));
    }

    @PutMapping("/repartidor/{id}")
    Envio replaceEmployee(@RequestBody Repartidor newRepartidor, @PathVariable Long id) {

        //Aquí puedes obtener un listado de los envios y escoger el que esté sin asignar aun
        // al repartidor, te pongo una respuesta estatica de ejemplo que contiene el primer envio del repositorio
        return envioRepo.findAll().iterator().next();
        
        
        
//        return repartidorRepo.findById(id)
//                .map(repartidor -> {
//                    repartidor.setLatitud(newRepartidor.getLatitud());
//                    repartidor.setLongitud(newRepartidor.getLongitud());
//                    return repartidorRepo.save(repartidor);
//                })
//                .orElseGet(() -> {
//                    newRepartidor.setId(id);
//                    return repartidorRepo.save(newRepartidor);
//                });
    }

    @DeleteMapping("/repartidor/{id}")
    void deleteRepartidor(@PathVariable Long id) {
        repartidorRepo.deleteById(id);
    }
}
