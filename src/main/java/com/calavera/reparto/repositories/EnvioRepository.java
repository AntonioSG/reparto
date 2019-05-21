/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calavera.reparto.repositories;

/**
 *
 * @author Antonio
 */

import com.calavera.reparto.model.Envio;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "envio", path = "envio")
public interface EnvioRepository extends PagingAndSortingRepository<Envio, Long> {

//    List<Envio> findByDni(@Param("dni") String dni);
//    Envio findNearest(@Param ("latitud") Double latitud,@Param ("longitud")Double longitud);

}
