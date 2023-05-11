package com.svalero.tiendaRopa.service;


import com.svalero.tiendaRopa.domain.Pedido;
import com.svalero.tiendaRopa.exception.PedidoNotFoundException;

import java.util.List;

public interface PedidoService {

    List<Pedido> verTodo();

    List<Pedido> verPedidoPorNombre (String nombre);

    Pedido verPorIdPedido(long idPedido) throws PedidoNotFoundException;

    Pedido crearPedido (Pedido pedido);

    void borrarPedido(long idPedido) throws  PedidoNotFoundException;

    Pedido modificarPedido (long idPedido, Pedido pedidoModificado) throws PedidoNotFoundException;

    Pedido modificarPagadoPedido (long idPedido, Pedido pedidoModificado) throws  PedidoNotFoundException;

}
