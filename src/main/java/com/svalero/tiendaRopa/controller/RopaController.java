package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.ErrorMessage;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.service.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RopaController {

    @Autowired
    private RopaService ropaService;

    private final Logger logger = LoggerFactory.getLogger (RopaController.class);

    @GetMapping("/ropas")
    public ResponseEntity<List<Ropa>> verTodo(@RequestParam(name = "nombre", defaultValue = "")String nombre){
        logger.debug("empieza VerTodo");
        if (nombre.equals("")){
            logger.debug("Fin de VerTodo");
            return ResponseEntity.ok(ropaService.verTodo());
        } else if (!nombre.equals("")) {
            logger.debug("Fin de VerTodo");
            return ResponseEntity.ok(ropaService.verRopaPorNombre(nombre));
        }
        return null;
    }

    @GetMapping("/ropa/{idRopa}")
    public ResponseEntity<Ropa> verPorIdRopa (@PathVariable long idRopa) throws RopaNotFoundException {
        logger.debug("empieza verPorIdRopa");
        Ropa ropa = ropaService.verPorIdRopa(idRopa);
        logger.debug("Fin de verPorIdRopa");
        return ResponseEntity.ok(ropa);
    }

    @PostMapping("/ropas")
    public ResponseEntity<Ropa> crearRopa (@RequestBody Ropa ropa) {
        logger.debug("empieza crearRopa");
        Ropa nuevaRopa = ropaService.crearRopa(ropa);
        logger.debug("Fin de crearRopa");
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRopa);
    }

    @DeleteMapping("/ropa/{idRopa}")
    public ResponseEntity<Void> borrarRopa (@PathVariable long idRopa) throws RopaNotFoundException {
        logger.debug("empieza borrarRopa");
        ropaService.borrarRopa(idRopa);
        logger.debug("Fin borrarRopa");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ropa/{idRopa}")
    public ResponseEntity<Ropa> modificarRopa(@PathVariable long idRopa, @RequestBody Ropa ropa) throws RopaNotFoundException {
        logger.debug("empieza modificarRopa");
        Ropa ropaModificada = ropaService.modificarRopa(idRopa,ropa);
        logger.debug("Fin modificarRopa");
        return ResponseEntity.status(HttpStatus.OK).body(ropaModificada);
    }

    @PatchMapping("/ropa/{idRopa}")
    public ResponseEntity<Ropa> modificarPrecio(@PathVariable long idRopa, @RequestBody Ropa ropa) throws RopaNotFoundException {
        logger.debug("empieza modificarPrecio");
        Ropa ropaModificada = ropaService.modificarPrecioRopa(idRopa, ropa);
        logger.debug("Fin modificarPrecio");
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
