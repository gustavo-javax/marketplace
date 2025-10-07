package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    public final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody @Valid Usuario usuario){
        Usuario criado = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
        usuario.setId(id);
        Usuario atualizado = usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.DeletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
