package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.dto.ProdutoDTO;
import com.Marketplace.Marketplace.dto.ProdutoDTO;
import com.Marketplace.Marketplace.entity.Produto;
import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;
import com.Marketplace.Marketplace.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor

public class ProdutoController {

    private final ProdutoService produtoService;
    private final MarketplaceMapper mapper;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody @Valid ProdutoDTO dto){
        Produto produto = mapper.toEntity(dto);
        Produto criado = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(criado));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar(){
        List<Produto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(mapper.toProdutoDTOList(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id){
       Produto produto = produtoService.buscarProdutoPorId(id);
       return ResponseEntity.ok(mapper.toDTO(produto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoDTO dto) {
        Produto produto = mapper.toEntity(dto);
        produto.setId(id);
        Produto atualizado = produtoService.atualizarProduto(produto);
        return ResponseEntity.ok(mapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }



}
