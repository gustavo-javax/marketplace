package com.Marketplace.Marketplace.dto;

import lombok.Data;

import java.util.List;
@Data

public class CarrinhoDTO {
    private Long id;
    private UsuarioDTO usuario;
    private List<CarrinhoItemDTO> itens;
}
