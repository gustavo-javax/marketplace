package com.Marketplace.Marketplace.service;

import com.Marketplace.Marketplace.entity.Usuario;
import com.Marketplace.Marketplace.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    // ---------------- Usuário CRUD ----------------

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepo.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepo.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario existente = usuarioRepo.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza campos
        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        existente.setRole(usuario.getRole());

        // Atualiza senha apenas se enviada
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            existente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepo.save(existente);
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepo.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepo.deleteById(id);
    }

    // ---------------- UserDetailsService ----------------

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole()))
        );
    }
}
