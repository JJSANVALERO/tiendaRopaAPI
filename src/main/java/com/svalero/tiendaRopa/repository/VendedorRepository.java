package com.svalero.tiendaRopa.repository;

import com.svalero.tiendaRopa.domain.Vendedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends CrudRepository<Vendedor, Long> {

    List<Vendedor> findAll();

    List<Vendedor> findByNombre(String nombre);
}
