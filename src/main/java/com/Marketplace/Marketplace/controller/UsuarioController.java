package com.Marketplace.Marketplace.controller;

import com.Marketplace.Marketplace.dto.UsuarioDTO;
import com.Marketplace.Marketplace.dto.UsuarioResponseDTO;
import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;
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

    private final UsuarioService usuarioService;
    private final MarketplaceMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Usuario criado = usuarioService.criarUsuario(usuario);
        UsuarioResponseDTO responseDto = mapper.toResponseDTO(criado); // usa DTO de resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(mapper.toUsuarioResponseDTOList(usuarios)); // lista de resposta
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponseDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        usuario.setId(id);
        Usuario atualizado = usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.ok(mapper.toResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
