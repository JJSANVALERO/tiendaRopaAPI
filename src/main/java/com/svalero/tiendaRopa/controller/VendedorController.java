package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.domain.Vendedor;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;
import com.svalero.tiendaRopa.service.RopaService;
import com.svalero.tiendaRopa.service.VendedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    private final Logger logger = LoggerFactory.getLogger (RopaController.class);

    @GetMapping("/vendedores")
    public ResponseEntity<List<Vendedor>> verTodo(@RequestParam(name = "nombre", defaultValue = "")String nombre){
        if (nombre.equals("")){
            return ResponseEntity.ok(vendedorService.verTodo());
        } else if (!nombre.equals("")) {
            return ResponseEntity.ok(vendedorService.verVendedorPorNombre(nombre));
        }
        return null;
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Vendedor> verPorIdVendedor (@PathVariable long idVendedor) throws VendedorNotFoundException {
        Vendedor vendedor = vendedorService.verPorIdVendedor(idVendedor);
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping("/vendedores")
    public ResponseEntity<Vendedor> crearVendedor (@RequestBody Vendedor vendedor) {
        Vendedor nuevoVendedor = vendedorService.crearVendedor(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVendedor);
    }

    @DeleteMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Void> borrarVendedor (@PathVariable long idVendedor) throws VendedorNotFoundException {
        vendedorService.borrarVendedor(idVendedor);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Vendedor> modificarVendedor(@PathVariable long idVendedor, @RequestBody Vendedor vendedor) throws VendedorNotFoundException {
        Vendedor vendedorModificado = vendedorService.modificarVendedor(idVendedor,vendedor);
        return ResponseEntity.status(HttpStatus.OK).body(vendedorModificado);
    }

    @PatchMapping("/vendedor/{idVendedor}")
    public ResponseEntity<Vendedor> modificarBajaVendedor (@PathVariable long idVendedor, @RequestBody Vendedor vendedor) throws VendedorNotFoundException {
        Vendedor vendedorModificado = vendedorService.modificarBajaVendedor(idVendedor,vendedor);
        return ResponseEntity.noContent().build();
    }

}
