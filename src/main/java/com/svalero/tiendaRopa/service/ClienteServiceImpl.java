package com.svalero.tiendaRopa.service;

import com.svalero.tiendaRopa.domain.Cliente;
import com.svalero.tiendaRopa.exception.ClienteNotFoundException;
import com.svalero.tiendaRopa.repository.ClienteRepository;
import com.svalero.tiendaRopa.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cliente> verTodo() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> verClientePorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Cliente verPorIdCliente(long idCliente) throws ClienteNotFoundException{
        return clienteRepository.findById(idCliente)
                .orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void borrarCliente(long idCliente) throws ClienteNotFoundException{
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(ClienteNotFoundException::new);
        clienteRepository.delete(cliente);
    }

    @Override
    public Cliente modificarCliente(long idCliente, Cliente clienteModificado) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(ClienteNotFoundException::new);
        cliente.setNombre((clienteModificado.getNombre()));
        cliente.setFechaAlta(clienteModificado.getFechaAlta());
        cliente.setClubVip(clienteModificado.getClubVip());
        cliente.setNumeroPedidos(clienteModificado.getNumeroPedidos());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modificarClubVipCliente(long idCliente, Cliente clienteModificado) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(ClienteNotFoundException::new);
        cliente.setClubVip(clienteModificado.getClubVip());
        return clienteRepository.save(cliente);
    }
}
