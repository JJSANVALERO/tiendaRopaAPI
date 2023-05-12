package com.svalero.tiendaRopa.repository;

import com.svalero.tiendaRopa.domain.Ropa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RopaRepository extends CrudRepository<Ropa, Long> {

    List<Ropa> findAll();

    List<Ropa> findByNombre(String nombre);
}
