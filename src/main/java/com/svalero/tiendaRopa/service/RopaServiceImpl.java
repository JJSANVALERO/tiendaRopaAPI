package com.svalero.tiendaRopa.service;

import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.repository.PedidoRepository;
import com.svalero.tiendaRopa.repository.RopaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RopaServiceImpl implements RopaService{

    @Autowired
    private RopaRepository ropaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

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
    public Ropa modificarRopa(long idRopa, Ropa ropaModificada) throws RopaNotFoundException{
        Ropa ropa = ropaRepository.findById(idRopa)
                .orElseThrow(RopaNotFoundException::new);
        ropa.setNombre(ropaModificada.getNombre());
        ropa.setCode(ropaModificada.getCode());
        ropa.setFechaAlta(ropaModificada.getFechaAlta());
        ropa.setPrecio(ropaModificada.getPrecio());
        ropa.setHayStock(ropaModificada.getHayStock());
        return null;
    }

    @Override
    public Ropa modificarPrecioRopa(long idRopa, Ropa ropaModificada)throws RopaNotFoundException {
        Ropa ropa = ropaRepository.findById(idRopa)
                .orElseThrow(RopaNotFoundException::new);
        ropa.setPrecio(ropaModificada.getPrecio());
        return ropaRepository.save(ropa);
    }
}
