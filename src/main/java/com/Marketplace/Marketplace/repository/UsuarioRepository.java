package com.Marketplace.Marketplace.repository;

import com.Marketplace.Marketplace.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
