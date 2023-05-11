package com.svalero.tiendaRopa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVendedor;
    @Column
    private String nombre;
    @Column
    private LocalDate fechaAlta;
    @Column
    private LocalDate fechaBaja;
    @Column
    private double sueldo;
    @Column
    private Boolean trabajadorActivo;

    @OneToMany(mappedBy = "vendedor")
    @JsonBackReference(value = "vendedor_pedidos")
    private List<Pedido> pedidos;


}
