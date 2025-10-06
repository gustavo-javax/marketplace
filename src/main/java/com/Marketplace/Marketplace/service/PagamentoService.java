package com.Marketplace.Marketplace.service;


import com.Marketplace.Marketplace.entity.Pagamento;
import com.Marketplace.Marketplace.entity.Pedido;
import com.Marketplace.Marketplace.entity.enums.StatusPagamento;
import com.Marketplace.Marketplace.entity.enums.StatusPedido;
import com.Marketplace.Marketplace.repository.PagamentoRepository;
import com.Marketplace.Marketplace.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class PagamentoService {
    private final PagamentoRepository pagamentoRepo;
    private final PedidoRepository pedidoRepo;

    public Pagamento processarPagamento(Pagamento pagamento){
        Pedido pedido = pedidoRepo.findById(pagamento.getPedido().getId())
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));
        pagamento.setStatus(StatusPagamento.APROVADO);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setPedido(pedido);

        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepo.save(pedido);

        return pagamentoRepo.save(pagamento);
    }
    public Pagamento buscarPorId(Long id) {
        return pagamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }



}
