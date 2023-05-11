package com.svalero.tiendaRopa.service;

import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.repository.RopaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RopaServiceImpl implements RopaService{

    @Autowired
    private RopaRepository ropaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Ropa> verTodo() {
        return ropaRepository.findAll();
    }

    @Override
    public List<Ropa> verRopaPorNombre(String nombre) {
        return ropaRepository.findByNombre(nombre);
    }

    @Override
    public Ropa verPorIdRopa(long idRopa) throws RopaNotFoundException {
        return ropaRepository.findById(idRopa)
                .orElseThrow(RopaNotFoundException::new);
    }

    @Override
    public Ropa crearRopa(Ropa ropa) {
        return ropaRepository.save(ropa);
    }

    @Override
    public void borrarRopa(long idRopa) throws RopaNotFoundException{
        Ropa ropa = ropaRepository.findById(idRopa)
                .orElseThrow(RopaNotFoundException::new);
        ropaRepository.delete(ropa);
    }

    @Override
    public Ropa modificarRopa(long idRopa, Ropa ropaModificada) {
        return null;
    }

    @Override
    public Ropa modificarPrecioRopa(long idRopa, Ropa ropaModificada) {
        return null;
    }
}
