package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Produto;
import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepo;

    public Produto criarProduto(@Valid Produto produto){
        return produtoRepo.save(produto);
    }

    public List<Produto> listarTodosProdutos(){
        return produtoRepo.findAll();
    }

    public Produto buscarProdutoPorId(Long id){
       return produtoRepo.findById(id).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
    }

    public Produto atualizarProduto(@Valid Produto produto){
        if (!produtoRepo.existsById(produto.getId())) {
            throw new RuntimeException("Produto não encontrado");
        }
        return produtoRepo.save(produto);
    }

    public void deletarProduto(Long id){
        if (!produtoRepo.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepo.deleteById(id);
    }

}
