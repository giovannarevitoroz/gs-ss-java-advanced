package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.ConfirmaPostagemDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.*;
import com.fiap.gs_swift_safety.repository.ConfirmaPostagemRepository;
import com.fiap.gs_swift_safety.repository.PostagemRepository;
import com.fiap.gs_swift_safety.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfirmaPostagemService {

    private final ConfirmaPostagemRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PostagemRepository postagemRepository;

    public ConfirmaPostagemService(
            ConfirmaPostagemRepository repository,
            UsuarioRepository usuarioRepository,
            PostagemRepository postagemRepository
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.postagemRepository = postagemRepository;
    }

    public List<ConfirmaPostagemDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ConfirmaPostagemDTO findById(Long usuarioId, Long postagemId) {
        ConfirmaPostagemId id = new ConfirmaPostagemId(usuarioId, postagemId);
        ConfirmaPostagem entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Confirmação não encontrada."));
        return toDTO(entity);
    }

    public ConfirmaPostagemDTO create(ConfirmaPostagemDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        Postagem postagem = postagemRepository.findById(dto.getPostagemId())
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada."));

        ConfirmaPostagem entity = new ConfirmaPostagem();
        entity.setId(new ConfirmaPostagemId(dto.getUsuarioId(), dto.getPostagemId()));
        entity.setUsuario(usuario);
        entity.setPostagem(postagem);
        entity.setDataConfirma(dto.getDataConfirma() != null ? dto.getDataConfirma() : new Date());

        return toDTO(repository.save(entity));
    }

    public void delete(Long usuarioId, Long postagemId) {
        ConfirmaPostagemId id = new ConfirmaPostagemId(usuarioId, postagemId);
        ConfirmaPostagem entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Confirmação não encontrada."));
        repository.delete(entity);
    }

    public List<ConfirmaPostagemDTO> findByUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        return repository.findByUsuario(usuario).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ConfirmaPostagemDTO> findByPostagem(Long postagemId) {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada."));
        return repository.findByPostagem(postagem).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ConfirmaPostagemDTO toDTO(ConfirmaPostagem entity) {
        ConfirmaPostagemDTO dto = new ConfirmaPostagemDTO();
        dto.setUsuarioId(entity.getUsuario().getIdUsuario());
        dto.setPostagemId(entity.getPostagem().getIdPostagem());
        dto.setDataConfirma(entity.getDataConfirma());
        return dto;
    }
}
