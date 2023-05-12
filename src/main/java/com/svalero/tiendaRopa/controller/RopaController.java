package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.service.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class RopaController {

    @Autowired
    private RopaService ropaService;

    private final Logger logger = LoggerFactory.getLogger (RopaController.class);

    @GetMapping("/ropas")
    public ResponseEntity<List<Ropa>> verTodo(@RequestParam(name = "nombre", defaultValue = "")String nombre){
        if (nombre.equals("")){
            return ResponseEntity.ok(ropaService.verTodo());
        } else if (!nombre.equals("")) {
            return ResponseEntity.ok(ropaService.verRopaPorNombre(nombre));
        }
        return null;
    }

    @GetMapping("/ropa/{idRopa}")
    public ResponseEntity<Ropa> verPorIdRopa (@PathVariable long idRopa) throws RopaNotFoundException {
        Ropa ropa = ropaService.verPorIdRopa(idRopa);
        return ResponseEntity.ok(ropa);
    }

    @PostMapping("/ropas")
    public ResponseEntity<Ropa> crearRopa (@RequestBody Ropa ropa) {
        Ropa nuevaRopa = ropaService.crearRopa(ropa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRopa);
    }

    @DeleteMapping("/ropa/{idRopa}")
    public ResponseEntity<Void> borrarRopa (@PathVariable long idRopa) throws RopaNotFoundException {
        ropaService.borrarRopa(idRopa);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ropa/{idRopa}")
    public ResponseEntity<Ropa> modificarRopa(@PathVariable long idRopa, @RequestBody Ropa ropa) throws RopaNotFoundException {
        Ropa ropaModificada = ropaService.modificarRopa(idRopa,ropa);
        return ResponseEntity.status(HttpStatus.OK).body(ropaModificada);
    }

    @PatchMapping("/ropa/{idRopa}")
    public ResponseEntity<Ropa> modificarPrecio(@PathVariable long idRopa, @RequestBody Ropa ropa) throws RopaNotFoundException {
        Ropa ropaModificada = ropaService.modificarPrecioRopa(idRopa, ropa);
        return ResponseEntity.noContent().build();
    }
}
