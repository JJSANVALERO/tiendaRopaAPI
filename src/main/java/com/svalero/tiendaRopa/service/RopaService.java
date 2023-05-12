package com.svalero.tiendaRopa.service;


import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;

import java.util.List;

public interface RopaService {

    List<Ropa> verTodo();

    List<Ropa> verRopaPorNombre (String nombre);

    Ropa verPorIdRopa(long idRopa) throws RopaNotFoundException;

    Ropa crearRopa (Ropa ropa);

    void borrarRopa(long idRopa) throws RopaNotFoundException;

    Ropa modificarRopa (long idRopa, Ropa ropaModificada) throws RopaNotFoundException;

    Ropa modificarPrecioRopa (long idRopa, Ropa ropaModificada) throws RopaNotFoundException;
}
