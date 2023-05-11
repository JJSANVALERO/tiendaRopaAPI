package com.svalero.tiendaRopa.service;

import com.svalero.tiendaRopa.domain.Cliente;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;

import java.util.List;

public interface ClienteService {

    List<Cliente> verTodo();

    List<Cliente> verClientePorNombre (String nombre);

    Cliente verPorIdCliente(long idCliente) throws ClienteNotFoundException;

    Cliente crearCliente (Cliente cliente);

    void borrarCliente(long idCliente) throws ClienteNotFoundException;

    Cliente modificarCliente (long idCliente, Cliente clienteModificado) throws ClienteNotFoundException;

    Cliente modificarClubVipCliente (long idCliente, Cliente clienteModificado) throws ClienteNotFoundException;
}
