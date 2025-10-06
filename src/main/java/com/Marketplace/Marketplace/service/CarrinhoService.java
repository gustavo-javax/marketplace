package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Carrinho;
import com.Marketplace.Marketplace.entity.CarrinhoItem;
import com.Marketplace.Marketplace.entity.Produto;
import com.Marketplace.Marketplace.repository.CarrinhoItemRepository;
import com.Marketplace.Marketplace.repository.CarrinhoRepository;
import com.Marketplace.Marketplace.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepo;
    private final CarrinhoItemRepository carrinhoItemRepo;
    private final ProdutoRepository produtoRepo;

    public Carrinho criarCarrinho(Carrinho carrinho){
        return carrinhoRepo.save(carrinho);
    }

    public List<Carrinho> listarTodosCarrinho(){
        return carrinhoRepo.findAll();
    }

    public Optional<Carrinho> buscarCarrinhoPorId(Long id){
        return carrinhoRepo.findById(id);
    }

    public void removerItem(Long itemId){
        carrinhoItemRepo.deleteById(itemId);
    }

    public void limparCarrinho(Long carrinhoId){
        Carrinho carrinho = carrinhoRepo.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho nao encontrado"));
        carrinho.getItens().clear();
        carrinhoRepo.save(carrinho);
    }

    public Carrinho adicionarItem (Carrinho carrinho, Long produtoId, int quantidade){
        Produto produto = produtoRepo.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
        if(produto.getEstoque() < quantidade){
            throw new RuntimeException("Estoque insuficiente");
        }
        CarrinhoItem item = CarrinhoItem.builder()
                .carrinho(carrinho)
                .produto(produto)
                .quantidade(quantidade)
                .precoUnitario(produto.getPreco())
                .build();
        carrinho.getItens().add(item);
        carrinho.addItem(item);
        return carrinhoRepo.save(carrinho);
    }


}
