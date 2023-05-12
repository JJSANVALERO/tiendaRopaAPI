package com.svalero.tiendaRopa.repository;

import com.svalero.tiendaRopa.domain.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    List<Pedido> findAll();
}
