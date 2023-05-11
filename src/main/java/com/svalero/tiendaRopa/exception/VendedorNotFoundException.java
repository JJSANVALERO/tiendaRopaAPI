package com.svalero.tiendaRopa.exception;

public class VendedorNotFoundException extends Exception{

    public VendedorNotFoundException(){
        super("Vendedor no encontrado");
    }

    public VendedorNotFoundException(String mensaje) {
        super(mensaje);
    }
}
