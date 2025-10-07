package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.dto.PedidoDTO;
import com.Marketplace.Marketplace.entity.Pedido;
import com.Marketplace.Marketplace.entity.enums.StatusPedido;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;
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
    private final MarketplaceMapper mapper;

    @PostMapping
    public ResponseEntity<PedidoDTO> criar(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = mapper.toEntity(dto);
        Pedido criado = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(criado));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listar(){
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(mapper.toPedidoDTOList(pedidos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id){
        Pedido pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(mapper.toDTO(pedido));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido status){
        Pedido pedido = pedidoService.buscarPorId(id);
        Pedido atualizado = pedidoService.atualizarStatus(pedido, status);
        return ResponseEntity.ok(mapper.toDTO(atualizado));
    }

}
