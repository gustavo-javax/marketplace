package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Categoria;
import com.Marketplace.Marketplace.entity.Produto;
import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.repository.CategoriaRepository;
import com.Marketplace.Marketplace.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepo;
    private final CategoriaRepository categoriaRepo;

//    public Produto criarProduto(@Valid Produto produto){
//        return produtoRepo.save(produto);
//    }
    public Produto criarProduto(@Valid Produto produto) {
    Categoria categoria = categoriaRepo.findById(produto.getCategoria().getId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    produto.setCategoria(categoria);

    return produtoRepo.save(produto);
}



    public List<Produto> listarTodosProdutos(){
        return produtoRepo.findAll();
    }

    public Produto buscarProdutoPorId(Long id){
       return produtoRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto nao encontrado"));
    }

    public Produto atualizarProduto(@Valid Produto produto) {
        Produto produtoExistente = produtoRepo.findById(produto.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepo.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setEstoque(produto.getEstoque());
        produtoExistente.setCategoria(categoria);

        return produtoRepo.save(produtoExistente);
    }


    public void deletarProduto(Long id){
        if (!produtoRepo.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepo.deleteById(id);
    }

}
