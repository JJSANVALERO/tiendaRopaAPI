package com.svalero.tiendaRopa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPedido;
    @Column
    private LocalDate fechaPedido;
    @Column
    private String code;
    @Column
    @NonNull
    private Boolean pagado;
    @Column
    private double valorPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @ManyToMany
    @JoinTable(name = "pedidos_ropas",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "ropa_id"))
    private List<Ropa> ropas = new ArrayList<Ropa>();

//    @ManyToMany(mappedBy = "pedidos")
//    @JsonBackReference(value = "ropa_id")
//    private List<Ropa> ropas;
}
