package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Cliente;
import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;
import com.svalero.tiendaRopa.exception.ErrorMessage;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.service.ClienteService;
import com.svalero.tiendaRopa.service.RopaService;
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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private final Logger logger = LoggerFactory.getLogger (ClienteController.class);

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> verTodo(@RequestParam(name = "nombre", defaultValue = "")String nombre){
        logger.debug("empieza VerTodo de clientes");
        if (nombre.equals("")){
            logger.debug("fin de VerTodo Cliente");
            return ResponseEntity.ok(clienteService.verTodo());
        } else if (!nombre.equals("")) {
            logger.debug("fin de VerTodo Cliente");
            return ResponseEntity.ok(clienteService.verClientePorNombre(nombre));
        }
        return null;
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Cliente> verPorIdCliente (@PathVariable long idCliente) throws ClienteNotFoundException {
        logger.debug("Empieza VerPorIdCliente");
        Cliente cliente = clienteService.verPorIdCliente(idCliente);
        logger.debug("Fin de VerPorIdCliente");
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearCliente (@RequestBody Cliente cliente) {
        logger.debug("Empieza CrearCliente");
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        logger.debug("Fin de CrearCliente");
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @DeleteMapping("/cliente/{idCliente}")
    public ResponseEntity<Void> borrarCliente (@PathVariable long idCliente) throws ClienteNotFoundException {
        logger.debug("Empieza borrarCliente");
        clienteService.borrarCliente(idCliente);
        logger.debug("Fin de borrarCliente");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cliente/{idCliente}")
    public ResponseEntity<Cliente> modificarCliente(@PathVariable long idCliente, @RequestBody Cliente cliente) throws ClienteNotFoundException {
        logger.debug("Empieza modificarCliente");
        Cliente clienteModificado = clienteService.modificarCliente(idCliente, cliente);
        logger.debug("Fin de modificarCliente");
        return ResponseEntity.status(HttpStatus.OK).body(clienteModificado);
    }

    @PatchMapping("/cliente/{idCliente}")
    public ResponseEntity<Ropa> modificarClubVip(@PathVariable long idCliente, @RequestBody Cliente cliente) throws ClienteNotFoundException {
        logger.debug("Empieza modificarClubVip");
        Cliente clienteModificado = clienteService.modificarClubVipCliente(idCliente, cliente);
        logger.debug("Fin de modificarClubVip");
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
