package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.UsuarioDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Usuario;
import com.fiap.gs_swift_safety.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Converter Entity para DTO
    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmailUsuario(usuario.getEmailUsuario());
        dto.setSenhaUsuario(usuario.getSenhaUsuario());
        dto.setTelefoneUsuario(usuario.getTelefoneUsuario());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        return dto;
    }

    // Converter DTO para Entity
    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setSenhaUsuario(passwordEncoder.encode(dto.getSenhaUsuario()));
        usuario.setTelefoneUsuario(dto.getTelefoneUsuario());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        return usuario;
    }

    // CRUD Operations
    public List<UsuarioDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Page<UsuarioDTO> findAllPaginated(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDTO);
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return toDTO(usuario);
    }

    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = repository.findByEmailUsuario(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
        return toDTO(usuario);
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        if (repository.findByEmailUsuario(dto.getEmailUsuario()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Usuario usuario = toEntity(dto);
        usuario = repository.save(usuario);
        return toDTO(usuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setTelefoneUsuario(dto.getTelefoneUsuario());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        // Atualiza senha apenas se foi alterada
        if (dto.getSenhaUsuario() != null && !dto.getSenhaUsuario().isEmpty()) {
            usuario.setSenhaUsuario(passwordEncoder.encode(dto.getSenhaUsuario()));
        }

        usuario = repository.save(usuario);
        return toDTO(usuario);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Custom Queries
    public Page<UsuarioDTO> findByNomeUsuario(String nome, Pageable pageable) {
        return repository.findByNomeUsuario(nome, pageable)
                .map(this::toDTO);
    }

    public List<UsuarioDTO> findAllOrderByNomeAsc() {
        return repository.findAllByOrderByNomeUsuarioAsc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> findAllOrderByNomeDesc() {
        return repository.findAllByOrderByNomeUsuarioDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}