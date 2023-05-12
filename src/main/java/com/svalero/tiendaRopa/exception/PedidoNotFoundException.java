package com.svalero.tiendaRopa.exception;

public class PedidoNotFoundException extends Exception{

    public PedidoNotFoundException() {
        super("Pedido no encontrado");
    }

    public PedidoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
