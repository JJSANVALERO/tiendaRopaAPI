package com.svalero.tiendaRopa.repository;

import com.svalero.tiendaRopa.domain.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    List<Cliente> findAll();

    List<Cliente> findByNombre(String nombre);
}
