package com.Marketplace.Marketplace.dto;

import com.Marketplace.Marketplace.entity.enums.StatusPagamento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data

public class PagamentoDTO {
    private Long id;
    private PedidoDTO pedido;
    private BigDecimal valor;
    private StatusPagamento status;
    private LocalDateTime dataPagamento;
}
