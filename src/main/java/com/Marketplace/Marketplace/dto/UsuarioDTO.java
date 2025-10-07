package com.Marketplace.Marketplace.dto;

import com.Marketplace.Marketplace.entity.enums.Role;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private Role role;

}
