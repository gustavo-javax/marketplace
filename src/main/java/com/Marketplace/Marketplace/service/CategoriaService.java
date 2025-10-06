package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Categoria;
import com.Marketplace.Marketplace.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoriaService {
    private final CategoriaRepository categoriaRepo;

    public Categoria criarCategoria(Categoria categoria){
        if (categoriaRepo.existsByNome(categoria.getNome())){
            throw new RuntimeException("Categoria ja existe");
        }
        return categoriaRepo.save(categoria);
    }

    public List<Categoria> listarTodasCategorias(){
        return categoriaRepo.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id){
        return categoriaRepo.findById(id);
    }

    public Categoria atualizarCategoria(Categoria categoria){
        if (!categoriaRepo.existsById(categoria.getId())){
            throw  new RuntimeException("Categoria nao encontrada");
        }
        return categoriaRepo.save(categoria);
    }

    public void deletarCategoria(Long id){
        if (!categoriaRepo.existsById(id)){
            throw new RuntimeException("Categoria nao encontrada");
        }
        categoriaRepo.deleteById(id);
    }

}
