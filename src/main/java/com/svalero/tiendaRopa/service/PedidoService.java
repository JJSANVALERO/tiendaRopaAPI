package com.svalero.tiendaRopa.service;


import com.svalero.tiendaRopa.domain.Pedido;
import com.svalero.tiendaRopa.domain.dto.PedidoInDTO;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;
import com.svalero.tiendaRopa.exception.PedidoNotFoundException;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;

import java.util.List;

public interface PedidoService {

    List<Pedido> verTodo();

    Pedido verPorIdPedido(long idPedido) throws PedidoNotFoundException;

    Pedido crearPedido (PedidoInDTO pedidoInDTO) throws ClienteNotFoundException, VendedorNotFoundException, RopaNotFoundException;

    void borrarPedido(long idPedido) throws  PedidoNotFoundException;

    Pedido modificarPedido (long idPedido, Pedido pedidoModificado) throws PedidoNotFoundException;

    Pedido modificarPagadoPedido (long idPedido, Pedido pedidoModificado) throws  PedidoNotFoundException;

}
