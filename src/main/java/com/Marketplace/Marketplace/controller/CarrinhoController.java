package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.entity.Carrinho;
import com.Marketplace.Marketplace.service.CarrinhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinhos")
@RequiredArgsConstructor


public class CarrinhoController {
    private final CarrinhoService carrinhoService;

    @PostMapping
    public ResponseEntity<Carrinho> criar(@RequestBody @Valid Carrinho carrinho) {
        Carrinho criado = carrinhoService.criarCarrinho(carrinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> listar() {
        return ResponseEntity.ok(carrinhoService.listarTodosCarrinho());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> buscarPorId(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorId(id);
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity<Carrinho> adicionarItem(
        @PathVariable Long id,
        @RequestParam Long produtoId,
        @RequestParam int quantidade){

        Carrinho carrinho = carrinhoService.buscarCarrinhoPorId(id);
        Carrinho atualizado = carrinhoService.adicionarItem(carrinho,produtoId,quantidade);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}/remover")
    public ResponseEntity<Void> removerItem(@RequestParam Long itemId){
        carrinhoService.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/limpar")
    public ResponseEntity<Void> limparCarrinho(@PathVariable Long id){
        carrinhoService.limparCarrinho(id);
        return ResponseEntity.noContent().build();
    }


}

