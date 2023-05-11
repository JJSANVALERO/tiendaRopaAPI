package com.svalero.tiendaRopa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

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
    private Boolean clubVip;
    @Column
    private int numeroPedidos;



}
