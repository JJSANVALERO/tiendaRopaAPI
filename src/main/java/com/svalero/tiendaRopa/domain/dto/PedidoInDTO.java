package com.svalero.tiendaRopa.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoInDTO {

    private LocalDate fechaPedido;
    private String code;
    private Boolean pagado;
    private double valorPedido;
    private long cliente;
    private long vendedor;
    private long ropas;
}
