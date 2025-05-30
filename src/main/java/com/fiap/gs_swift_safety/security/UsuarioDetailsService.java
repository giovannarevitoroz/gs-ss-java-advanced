package com.fiap.gs_swift_safety.security;

import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Usuario;
import com.fiap.gs_swift_safety.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailUsuario(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));

        return new User(
                usuario.getEmailUsuario(),
                usuario.getSenhaUsuario(),
                getAuthorities(usuario)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        // Aqui assumo que tipo_usuario é uma String representando o papel, ex: "ROLE_ADMIN"
        // Você pode adaptar se tiver mais de um papel ou usar uma lista de papéis
        return Collections.singletonList(new SimpleGrantedAuthority(usuario.getTipoUsuario()));
    }
}
