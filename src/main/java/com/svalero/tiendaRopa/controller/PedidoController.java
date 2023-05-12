package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Pedido;
import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.domain.dto.PedidoInDTO;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;
import com.svalero.tiendaRopa.exception.PedidoNotFoundException;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;
import com.svalero.tiendaRopa.service.PedidoService;
import com.svalero.tiendaRopa.service.PedidoServiceImpl;
import com.svalero.tiendaRopa.service.RopaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    private final Logger logger = LoggerFactory.getLogger (RopaController.class);

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> verTodo(){
        return ResponseEntity.ok(pedidoService.verTodo());
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<Pedido> verPorIdPedido (@PathVariable long idPedido) throws PedidoNotFoundException {
        Pedido pedido = pedidoService.verPorIdPedido(idPedido);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> crearPedido (@Valid @RequestBody PedidoInDTO pedidoInDTO) throws ClienteNotFoundException, VendedorNotFoundException, RopaNotFoundException {
        Pedido nuevoPedido = pedidoService.crearPedido(pedidoInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @DeleteMapping("/pedido/{idPedido}")
    public ResponseEntity<Void> borrarPedido (@PathVariable long idPedido) throws PedidoNotFoundException {
        pedidoService.borrarPedido(idPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pedido/{idPedido}")
    public ResponseEntity<Pedido> modificarPedido (@PathVariable long idPedido, @RequestBody Pedido pedido) throws PedidoNotFoundException {
        Pedido pedidoModificado = pedidoService.modificarPedido(idPedido, pedido);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoModificado);
    }

    @PatchMapping("pedido/{idPedido}")
    public ResponseEntity<Pedido> modificarPagado(@PathVariable long idPedido, @RequestBody Pedido pedido) throws PedidoNotFoundException {
        Pedido pedidoModificado = pedidoService.modificarPagadoPedido(idPedido,pedido);
        return ResponseEntity.noContent().build();
    }
}
