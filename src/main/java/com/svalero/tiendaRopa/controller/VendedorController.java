package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.domain.Vendedor;
import com.svalero.tiendaRopa.exception.ErrorMessage;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;
import com.svalero.tiendaRopa.service.RopaService;
import com.svalero.tiendaRopa.service.VendedorService;
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
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    private final Logger logger = LoggerFactory.getLogger (VendedorController.class);

    @GetMapping("/vendedores")
    public ResponseEntity<List<Vendedor>> verTodo(@RequestParam(name = "nombre", defaultValue = "")String nombre){
        logger.debug("empieza VerTodo");
        if (nombre.equals("")){
            logger.debug("Fin de VerTodo");
            return ResponseEntity.ok(vendedorService.verTodo());
        } else if (!nombre.equals("")) {
            logger.debug("Fin de VerTodo");
            return ResponseEntity.ok(vendedorService.verVendedorPorNombre(nombre));
        }
        return null;
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Vendedor> verPorIdVendedor (@PathVariable long idVendedor) throws VendedorNotFoundException {
        logger.debug("empieza verPorIdVendedor");
        Vendedor vendedor = vendedorService.verPorIdVendedor(idVendedor);
        logger.debug("Fin verPorIdVendedor");
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping("/vendedores")
    public ResponseEntity<Vendedor> crearVendedor (@RequestBody Vendedor vendedor) {
        logger.debug("empieza crearVendedor");
        Vendedor nuevoVendedor = vendedorService.crearVendedor(vendedor);
        logger.debug("Fin de crearVendedor");
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVendedor);
    }

    @DeleteMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Void> borrarVendedor (@PathVariable long idVendedor) throws VendedorNotFoundException {
        logger.debug("empieza borrarVendedor");
        vendedorService.borrarVendedor(idVendedor);
        logger.debug("Fin de borrarVendedor");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Vendedor> modificarVendedor(@PathVariable long idVendedor, @RequestBody Vendedor vendedor) throws VendedorNotFoundException {
        logger.debug("empieza modificarVendedor");
        Vendedor vendedorModificado = vendedorService.modificarVendedor(idVendedor,vendedor);
        logger.debug("Fin de modificarVendedor");
        return ResponseEntity.status(HttpStatus.OK).body(vendedorModificado);
    }

    @PatchMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Vendedor> modificarBajaVendedor (@PathVariable long idVendedor, @RequestBody Vendedor vendedor) throws VendedorNotFoundException {
        logger.debug("empieza modificarBajaVendedor");
        Vendedor vendedorModificado = vendedorService.modificarBajaVendedor(idVendedor,vendedor);
        logger.debug("Fin de modificarBajaVendedor");
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
