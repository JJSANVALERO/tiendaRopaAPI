package com.svalero.tiendaRopa.exception;

public class RopaNotFoundException extends Exception{

    public RopaNotFoundException() {
        super("Ropa no encontrada");
    }

    public RopaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
