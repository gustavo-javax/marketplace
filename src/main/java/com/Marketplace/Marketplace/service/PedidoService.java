package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.*;
import com.Marketplace.Marketplace.entity.enums.StatusPedido;
import com.Marketplace.Marketplace.repository.PedidoItemRepositoy;
import com.Marketplace.Marketplace.repository.PedidoRepository;
import com.Marketplace.Marketplace.repository.ProdutoRepository;
import com.Marketplace.Marketplace.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.Marketplace.Marketplace.entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProdutoRepository produtoRepo;
    private final CarrinhoService carrinhoService;
    @Autowired
    private UsuarioRepository usuarioRepo;

    public Pedido criarPedido(Pedido pedido) {
        Usuario usuario = usuarioRepo.findById(pedido.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        pedido.setUsuario(usuario);

        Carrinho carrinho = carrinhoService.buscarPorUsuario(pedido.getUsuario().getId());
        if (carrinho.getItens().isEmpty()) {
            throw new RuntimeException("Carrinho vazio");
        }




        List<PedidoItem> itensPedido = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CarrinhoItem carrinhoItem : carrinho.getItens()) {
            Produto produto = produtoRepo.findById(carrinhoItem.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getEstoque() < carrinhoItem.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para " + produto.getNome());
            }

            PedidoItem pedidoItem = new PedidoItem();
            pedidoItem.setProduto(produto);
            pedidoItem.setQuantidade(carrinhoItem.getQuantidade());
            pedidoItem.setPrecoUnitario(produto.getPreco());
            pedidoItem.setPedido(pedido);

            itensPedido.add(pedidoItem);

            total = total.add(produto.getPreco().multiply(BigDecimal.valueOf(carrinhoItem.getQuantidade())));

            produto.setEstoque(produto.getEstoque() - carrinhoItem.getQuantidade());
            produtoRepo.save(produto);
        }

        pedido.setItems(itensPedido);
        pedido.setTotal(total);
        pedido.setStatus(StatusPedido.CRIADO);
        pedido.setDataCriacao(LocalDateTime.now());

        carrinhoService.limparCarrinho(carrinho.getId());

        return pedidoRepo.save(pedido);
    }





    public List<Pedido> listarTodos() {
        return pedidoRepo.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido nao encontrado"));
    }

    public Pedido atualizarStatus(Pedido pedido, StatusPedido status) {
        pedido.setStatus(status);
        return pedidoRepo.save(pedido);
    }

}
