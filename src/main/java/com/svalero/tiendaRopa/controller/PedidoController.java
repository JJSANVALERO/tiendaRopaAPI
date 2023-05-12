package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Pedido;
import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.domain.dto.PedidoInDTO;
import com.svalero.tiendaRopa.exception.*;
import com.svalero.tiendaRopa.service.PedidoService;
import com.svalero.tiendaRopa.service.PedidoServiceImpl;
import com.svalero.tiendaRopa.service.RopaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    private final Logger logger = LoggerFactory.getLogger (PedidoController.class);

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> verTodo(){
        return ResponseEntity.ok(pedidoService.verTodo());
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<Pedido> verPorIdPedido (@PathVariable long idPedido) throws PedidoNotFoundException {
        logger.debug("empieza verPorIdPedido");
        Pedido pedido = pedidoService.verPorIdPedido(idPedido);
        logger.debug("Fin de verPorIdPedido");
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> crearPedido (@Valid @RequestBody PedidoInDTO pedidoInDTO) throws ClienteNotFoundException, VendedorNotFoundException, RopaNotFoundException {
        logger.debug("empieza crearPedido");
        Pedido nuevoPedido = pedidoService.crearPedido(pedidoInDTO);
        logger.debug("Fin de crearPedido");
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @DeleteMapping("/pedido/{idPedido}")
    public ResponseEntity<Void> borrarPedido (@PathVariable long idPedido) throws PedidoNotFoundException {
        logger.debug("empieza borrarPedido");
        pedidoService.borrarPedido(idPedido);
        logger.debug("Fin borrarPedido");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pedido/{idPedido}")
    public ResponseEntity<Pedido> modificarPedido (@PathVariable long idPedido, @RequestBody Pedido pedido) throws PedidoNotFoundException {
        logger.debug("empieza modificarPedido");
        Pedido pedidoModificado = pedidoService.modificarPedido(idPedido, pedido);
        logger.debug("Fin de modificarPedido");
        return ResponseEntity.status(HttpStatus.OK).body(pedidoModificado);
    }

    @PatchMapping("pedido/{idPedido}")
    public ResponseEntity<Pedido> modificarPagado(@PathVariable long idPedido, @RequestBody Pedido pedido) throws PedidoNotFoundException {
        logger.debug("empieza modificarPagado");
        Pedido pedidoModificado = pedidoService.modificarPagadoPedido(idPedido,pedido);
        logger.debug("Fin de modificarPedido");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve){
        logger.error(manve.getMessage(), manve);
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception){
        logger.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorMessage> handleNumberFormatException(NumberFormatException exception) {
        ErrorMessage errorMessage = new ErrorMessage(400, "Formato de parametro no valido");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
