package com.Marketplace.Marketplace.repository;

import com.Marketplace.Marketplace.entity.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {
}
