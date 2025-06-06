package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.UsuarioDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Usuario;
import com.fiap.gs_swift_safety.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper mapper;

    private void validarTipoUsuario(String tipoUsuario) {
        if (!"Usuário".equalsIgnoreCase(tipoUsuario) && !"Funcionário".equalsIgnoreCase(tipoUsuario)) {
            throw new IllegalArgumentException("Tipo de usuário inválido. Deve ser 'Usuário' ou 'Funcionário'");
        }
    }

    public List<UsuarioDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(u -> mapper.map(u, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public Page<UsuarioDTO> findAllPaginated(Pageable pageable) {
        return repository.findAll(pageable)
                .map(u -> mapper.map(u, UsuarioDTO.class));
    }

    public UsuarioDTO findById(Long id) {
        return repository.findById(id)
                .map(u -> mapper.map(u, UsuarioDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public UsuarioDTO findByEmail(String email) {
        return repository.findByEmailUsuario(email)
                .map(u -> mapper.map(u, UsuarioDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o e-mail informado"));
    }

    public Page<UsuarioDTO> findByNomeUsuario(String nome, Pageable pageable) {
        return repository.findByNomeUsuario(nome, pageable)
                .map(u -> mapper.map(u, UsuarioDTO.class));
    }

    public List<UsuarioDTO> findAllOrderByNomeAsc() {
        return repository.findAllByOrderByNomeUsuarioAsc()
                .stream()
                .map(u -> mapper.map(u, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> findAllOrderByNomeDesc() {
        return repository.findAllByOrderByNomeUsuarioDesc()
                .stream()
                .map(u -> mapper.map(u, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        validarTipoUsuario(dto.getTipoUsuario());
        dto.setIdUsuario(null); // garante que será INSERT
        Usuario u = repository.save(mapper.map(dto, Usuario.class));
        return mapper.map(u, UsuarioDTO.class);
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        validarTipoUsuario(dto.getTipoUsuario());
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para atualização"));
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setSenhaUsuario(dto.getSenhaUsuario());
        usuario.setTelefoneUsuario(dto.getTelefoneUsuario());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        Usuario atualizado = repository.save(usuario);
        return mapper.map(atualizado, UsuarioDTO.class);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado para exclusão");
        }
        repository.deleteById(id);
    }
}
