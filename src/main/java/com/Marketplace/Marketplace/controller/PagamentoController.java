package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.dto.PagamentoDTO;
import com.Marketplace.Marketplace.entity.Pagamento;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;
import com.Marketplace.Marketplace.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    public final PagamentoService pagamentoService;
    private final MarketplaceMapper mapper;

    @PostMapping
    public ResponseEntity<PagamentoDTO> processarPagamento(@RequestBody @Valid PagamentoDTO dto){
       Pagamento pagamento = mapper.toEntity(dto);
        Pagamento realizado = pagamentoService.processarPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(realizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id){
        Pagamento pagamento = pagamentoService.buscarPorId(id);
        return ResponseEntity.ok(mapper.toDTO(pagamento));
    }

}
