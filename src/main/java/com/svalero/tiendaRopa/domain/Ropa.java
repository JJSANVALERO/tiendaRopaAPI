package com.svalero.tiendaRopa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ropa")
public class Ropa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRopa;
    @Column
    private String nombre;
    @Column
    private String code;
    @Column
    private LocalDate fechaAlta;
    @Column
    private double precio;
    @Column
    @NonNull
    private Boolean hayStock;

    @ManyToMany(mappedBy = "ropas")
    @JsonBackReference(value = "pedido_id")
    private List<Pedido> pedidos;

//    @ManyToMany
//    @JoinTable(name = "ropas_pedidos",
//        joinColumns = @JoinColumn(name = "ropa_id"),
//        inverseJoinColumns = @JoinColumn(name = "pedido_id"))
//    private List<Pedido> pedidos = new ArrayList<Pedido>();
}
