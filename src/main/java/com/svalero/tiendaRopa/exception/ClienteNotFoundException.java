package com.svalero.tiendaRopa.exception;

public class ClienteNotFoundException extends Exception {

    public ClienteNotFoundException(){
        super("Cliente no encontrado");
    }

    public ClienteNotFoundException(String mensaje) {
        super(mensaje);
    }
}
