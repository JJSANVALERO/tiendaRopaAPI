package com.svalero.tiendaRopa.controller;

import com.svalero.tiendaRopa.domain.Cliente;
import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.service.ClienteService;
import com.svalero.tiendaRopa.service.RopaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private final Logger logger = LoggerFactory.getLogger (RopaController.class);

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> verTodo(@RequestParam(name = "nombre", defaultValue = "")String nombre){
        if (nombre.equals("")){
            return ResponseEntity.ok(clienteService.verTodo());
        } else if (!nombre.equals("")) {
            return ResponseEntity.ok(clienteService.verClientePorNombre(nombre));
        }
        return null;
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Cliente> verPorIdCliente (@PathVariable long idCliente) throws ClienteNotFoundException {
        Cliente cliente = clienteService.verPorIdCliente(idCliente);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearCliente (@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @DeleteMapping("/cliente/{idCliente}")
    public ResponseEntity<Void> borrarCliente (@PathVariable long idCliente) throws ClienteNotFoundException {
        clienteService.borrarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cliente/{idCliente}")
    public ResponseEntity<Cliente> modificarCliente(@PathVariable long idCliente, @RequestBody Cliente cliente) throws ClienteNotFoundException {
        Cliente clienteModificado = clienteService.modificarCliente(idCliente, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteModificado);
    }

    @PatchMapping("/cliente/{idCliente}")
    public ResponseEntity<Ropa> modificarClubVip(@PathVariable long idCliente, @RequestBody Cliente cliente) throws ClienteNotFoundException {
        Cliente clienteModificado = clienteService.modificarClubVipCliente(idCliente, cliente);
        return ResponseEntity.noContent().build();
    }
}
