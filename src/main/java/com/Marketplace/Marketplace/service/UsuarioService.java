package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class UsuarioService {
    private final UsuarioRepository usuarioRepo;

    public Usuario criarUsuario(Usuario usuario){
        if (usuarioRepo.existsByEmail(usuario.getEmail())){
            throw new RuntimeException("Email ja cadastrado");
        }
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepo.findById(id);
    }

    public Usuario atualizarUsuario(Usuario usuario){
        if (!usuarioRepo.existsById(usuario.getId())) {
            throw new RuntimeException("Usuario não encontrado");
        }
        return usuarioRepo.save(usuario);
        }

        public void DeletarUsuario(Long id){
        if (!usuarioRepo.existsById(id)){
            throw new RuntimeException("Usuario não encontrado");
        }
            usuarioRepo.deleteById(id);
        }



    }



