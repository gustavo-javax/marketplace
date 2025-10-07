package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.entity.Pedido;
import com.Marketplace.Marketplace.entity.enums.StatusPedido;
import com.Marketplace.Marketplace.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor

public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody @Valid Pedido pedido){
        Pedido criado = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id){
        Pedido pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido status){
        Pedido pedido = pedidoService.buscarPorId(id);
        Pedido atualizado = pedidoService.atualizarStatus(pedido, status);
        return ResponseEntity.ok(atualizado);
    }

}
