package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.dto.CarrinhoDTO;
import com.Marketplace.Marketplace.entity.Carrinho;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;
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
    private final MarketplaceMapper mapper;

    @PostMapping
    public ResponseEntity<CarrinhoDTO> criar(@RequestBody @Valid CarrinhoDTO dto) {
       Carrinho carrinho = mapper.toEntity(dto);
        Carrinho criado = carrinhoService.criarCarrinho(carrinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(criado));
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoDTO>> listar() {
        List<Carrinho> carrinhos = carrinhoService.listarTodosCarrinho();
        return ResponseEntity.ok(mapper.toCarrinhoDTOList(carrinhos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDTO> buscarPorId(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorId(id);
        return ResponseEntity.ok(mapper.toDTO(carrinho));
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity<CarrinhoDTO> adicionarItem(
        @PathVariable Long id,
        @RequestParam Long produtoId,
        @RequestParam int quantidade){

        Carrinho carrinho = carrinhoService.buscarCarrinhoPorId(id);
        Carrinho atualizado = carrinhoService.adicionarItem(carrinho,produtoId,quantidade);
        return ResponseEntity.ok(mapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}/remover")
    public ResponseEntity<Void> removerItem(@PathVariable Long id, @RequestParam Long itemId){
        carrinhoService.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/limpar")
    public ResponseEntity<Void> limparCarrinho(@PathVariable Long id){
        carrinhoService.limparCarrinho(id);
        return ResponseEntity.noContent().build();
    }


}

