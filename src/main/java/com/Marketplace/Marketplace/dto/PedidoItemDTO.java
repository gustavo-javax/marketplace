package com.Marketplace.Marketplace.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoItemDTO {
    private Long id;
    private ProdutoDTO produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
}
