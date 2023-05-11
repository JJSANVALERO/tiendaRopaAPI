package com.svalero.tiendaRopa.service;



import com.svalero.tiendaRopa.domain.Vendedor;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;

import java.util.List;

public interface VendedorService {

    List<Vendedor> verTodo();

    List<Vendedor> verVendedorPorNombre (String nombre);

    Vendedor verPorIdVendedor(long idVendedor) throws VendedorNotFoundException;

    Vendedor crearVendedor (Vendedor vendedor);

    void borrarVendedor(long idVendedor) throws VendedorNotFoundException;

    Vendedor modificarVendedor (long idVendedor, Vendedor vendedorModificado) throws VendedorNotFoundException;

    Vendedor modificarBajaVendedor (long idVendedor, Vendedor vendedorModificado) throws VendedorNotFoundException;
}
