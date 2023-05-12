package com.svalero.tiendaRopa.service;

import com.svalero.tiendaRopa.domain.Vendedor;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;
import com.svalero.tiendaRopa.repository.PedidoRepository;
import com.svalero.tiendaRopa.repository.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService{

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Vendedor> verTodo() {
        return vendedorRepository.findAll();
    }

    @Override
    public List<Vendedor> verVendedorPorNombre(String nombre) {
        return vendedorRepository.findByNombre(nombre);
    }

    @Override
    public Vendedor verPorIdVendedor(long idVendedor) throws VendedorNotFoundException {
        return vendedorRepository.findById(idVendedor)
                .orElseThrow(VendedorNotFoundException::new);
    }

    @Override
    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public void borrarVendedor(long idVendedor) throws VendedorNotFoundException {
        Vendedor vendedor =vendedorRepository.findById(idVendedor)
                .orElseThrow(VendedorNotFoundException::new);
        vendedorRepository.delete(vendedor);
    }

    @Override
    public Vendedor modificarVendedor(long idVendedor, Vendedor vendedorModificado) throws VendedorNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(idVendedor)
                .orElseThrow(VendedorNotFoundException::new);
        vendedor.setNombre(vendedorModificado.getNombre());
        vendedor.setFechaAlta(vendedorModificado.getFechaAlta());
        vendedor.setFechaBaja(vendedorModificado.getFechaBaja());
        vendedor.setSueldo(vendedorModificado.getSueldo());
        vendedor.setTrabajadorActivo(vendedorModificado.getTrabajadorActivo());
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor modificarBajaVendedor(long idVendedor, Vendedor vendedorModificado) throws VendedorNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(idVendedor)
                .orElseThrow(VendedorNotFoundException::new);
        vendedor.setFechaBaja(vendedorModificado.getFechaBaja());
        vendedor.setTrabajadorActivo(vendedorModificado.getTrabajadorActivo());
        return null;
    }
}
