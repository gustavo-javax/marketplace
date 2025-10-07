package com.Marketplace.Marketplace.dto;

import com.Marketplace.Marketplace.entity.enums.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data

public class PedidoDTO {
    private Long id;
    private UsuarioDTO usuario;
    private LocalDateTime dataCriacao;
    private BigDecimal total;
    private StatusPedido status;
    private List<PedidoItemDTO> items;
}
