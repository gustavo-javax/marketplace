package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.entity.Pagamento;
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

    @PostMapping
    public ResponseEntity<Pagamento> processarPagamento(@RequestBody @Valid Pagamento pagamento){
        Pagamento realizado = pagamentoService.processarPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(realizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id){
        Pagamento pagamento = pagamentoService.buscarPorId(id);
        return ResponseEntity.ok(pagamento);
    }

}
