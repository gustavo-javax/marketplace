package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.dto.CategoriaDTO;
import com.Marketplace.Marketplace.entity.Categoria;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;
import com.Marketplace.Marketplace.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")


public class CategoriaController {
    private final CategoriaService categoriaService;
    private final MarketplaceMapper mapper;

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@RequestBody @Valid CategoriaDTO dto){
       Categoria categoria = mapper.toEntity(dto);
        Categoria criado = categoriaService.criarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(criado));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar(){
        List<Categoria> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(mapper.toCategoriaDTOList(categorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);
        return categoria
                .map(value -> ResponseEntity.ok(mapper.toDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaDTO dto){
        Categoria categoria = mapper.toEntity(dto);
        categoria.setId(id);
        Categoria atualizado = categoriaService.atualizarCategoria(categoria);
        return ResponseEntity.ok(mapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }





}
