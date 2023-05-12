package com.svalero.tiendaRopa.service;

import com.svalero.tiendaRopa.domain.Cliente;
import com.svalero.tiendaRopa.domain.Pedido;
import com.svalero.tiendaRopa.domain.Ropa;
import com.svalero.tiendaRopa.domain.Vendedor;
import com.svalero.tiendaRopa.domain.dto.PedidoInDTO;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;
import com.svalero.tiendaRopa.exception.PedidoNotFoundException;
import com.svalero.tiendaRopa.exception.RopaNotFoundException;
import com.svalero.tiendaRopa.exception.VendedorNotFoundException;
import com.svalero.tiendaRopa.repository.ClienteRepository;
import com.svalero.tiendaRopa.repository.PedidoRepository;
import com.svalero.tiendaRopa.repository.RopaRepository;
import com.svalero.tiendaRopa.repository.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RopaRepository ropaRepository;

    @Autowired
    VendedorRepository vendedorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Pedido> verTodo() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido verPorIdPedido(long idPedido) throws PedidoNotFoundException {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(PedidoNotFoundException::new);
    }

    @Override
    public Pedido crearPedido(PedidoInDTO pedidoInDTO) throws ClienteNotFoundException, VendedorNotFoundException, RopaNotFoundException {
        Pedido nuevoPedido = new Pedido();
        modelMapper.map(pedidoInDTO, nuevoPedido);

        Cliente cliente = clienteRepository.findById(pedidoInDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);
        nuevoPedido.setCliente(cliente);

        Vendedor vendedor = vendedorRepository.findById(pedidoInDTO.getVendedor())
                .orElseThrow(VendedorNotFoundException::new);
        nuevoPedido.setVendedor(vendedor);

        Ropa ropa = ropaRepository.findById(pedidoInDTO.getRopas())
                .orElseThrow(RopaNotFoundException::new);
        List<Ropa> ropas = new ArrayList<Ropa>();
        ropas.add(ropa);
        nuevoPedido.setRopas(ropas);

        return pedidoRepository.save(nuevoPedido);
    }

    @Override
    public void borrarPedido(long idPedido) throws PedidoNotFoundException{
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(PedidoNotFoundException::new);
        pedidoRepository.delete(pedido);
    }

    @Override
    public Pedido modificarPedido(long idPedido, Pedido pedidoModificado) throws PedidoNotFoundException{
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(PedidoNotFoundException::new);
        pedido.setFechaPedido(pedidoModificado.getFechaPedido());
        pedido.setCode(pedidoModificado.getCode());
        pedido.setPagado(pedidoModificado.getPagado());
        pedido.setValorPedido(pedidoModificado.getValorPedido());
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido modificarPagadoPedido(long idPedido, Pedido pedidoModificado) throws PedidoNotFoundException {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(PedidoNotFoundException::new);
        pedido.setPagado(pedidoModificado.getPagado());
        return pedidoRepository.save(pedido);
    }
}
