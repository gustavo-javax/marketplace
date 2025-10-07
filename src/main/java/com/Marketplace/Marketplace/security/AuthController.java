package com.Marketplace.Marketplace.security;

import com.Marketplace.Marketplace.dto.UsuarioDTO;
import com.Marketplace.Marketplace.dto.UsuarioResponseDTO;
import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.entity.enums.Role;
import com.Marketplace.Marketplace.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Marketplace.Marketplace.mapper.MarketplaceMapper;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MarketplaceMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(Role.CLIENTE);
        Usuario criado = usuarioService.criarUsuario(usuario);
        UsuarioResponseDTO responseDto = mapper.toResponseDTO(criado);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.buscarPorEmail(dto.getEmail());
        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        String token = jwtService.generateToken(usuario.getEmail());
        return ResponseEntity.ok(token);
    }
}
