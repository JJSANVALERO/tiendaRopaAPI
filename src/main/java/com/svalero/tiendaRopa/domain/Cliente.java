package com.svalero.tiendaRopa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;
    @Column
    private String nombre;
    @Column
    private LocalDate fechaAlta;
    @Column
    @NonNull
    private Boolean clubVip;
    @Column
    private int numeroPedidos;

    @OneToMany(mappedBy = "cliente")
    @JsonBackReference(value = "cliente_pedidos")
    private List<Pedido> pedidos;


}
