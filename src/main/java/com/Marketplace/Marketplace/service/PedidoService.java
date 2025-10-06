package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Pedido;
import com.Marketplace.Marketplace.entity.PedidoItem;
import com.Marketplace.Marketplace.entity.Produto;
import com.Marketplace.Marketplace.entity.enums.StatusPedido;
import com.Marketplace.Marketplace.repository.PedidoItemRepositoy;
import com.Marketplace.Marketplace.repository.PedidoRepository;
import com.Marketplace.Marketplace.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProdutoRepository produtoRepo;

    public Pedido criarPedido(Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;

        for (PedidoItem item : pedido.getItems()) {
            Produto produto = produtoRepo.findById(item.getPedido().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para " + produto.getNome());
            }

            item.setPrecoUnitario(produto.getPreco());
            item.setPedido(pedido);
            total = total.add(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepo.save(produto);
        }

        pedido.setTotal(total);
        pedido.setStatus(StatusPedido.CRIADO);
        pedido.setDataCriacao(LocalDateTime.now());

        return pedidoRepo.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepo.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepo.findById(id);
    }

    public Pedido atualizarStatus(Pedido pedido, StatusPedido status) {
        pedido.setStatus(status);
        return pedidoRepo.save(pedido);
    }

}
