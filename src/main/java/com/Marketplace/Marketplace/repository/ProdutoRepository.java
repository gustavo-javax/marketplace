package com.Marketplace.Marketplace.repository;

import com.Marketplace.Marketplace.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
