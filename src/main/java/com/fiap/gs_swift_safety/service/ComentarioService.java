package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.ComentarioDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Comentario;
import com.fiap.gs_swift_safety.model.Postagem;
import com.fiap.gs_swift_safety.model.Usuario;
import com.fiap.gs_swift_safety.repository.ComentarioRepository;
import com.fiap.gs_swift_safety.repository.PostagemRepository;
import com.fiap.gs_swift_safety.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, PostagemRepository postagemRepository, UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ComentarioDTO salvar(ComentarioDTO dto) {
        Comentario comentario = toEntity(dto);
        return fromEntity(comentarioRepository.save(comentario));
    }

    public ComentarioDTO buscarPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com ID: " + id));
        return fromEntity(comentario);
    }

    public List<ComentarioDTO> buscarTodos() {
        return comentarioRepository.findAll().stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> buscarPorPostagem(Long postagemId) {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com ID: " + postagemId));
        return comentarioRepository.findByPostagem(postagem).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> buscarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + usuarioId));
        return comentarioRepository.findByUsuario(usuario).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> buscarPorPostagemOrdenadoPorData(Long postagemId) {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com ID: " + postagemId));
        return comentarioRepository.findByPostagemOrderByDataAsc(postagem).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com ID: " + id));
        comentarioRepository.delete(comentario);
    }

    public ComentarioDTO atualizar(Long id, ComentarioDTO dto) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com ID: " + id));

        comentario.setTexto(dto.getTexto());
        comentario.setData(dto.getData());
        comentario.setPostagem(postagemRepository.findById(dto.getPostagemId())
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com ID: " + dto.getPostagemId())));
        comentario.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId())));

        return fromEntity(comentarioRepository.save(comentario));
    }

    private Comentario toEntity(ComentarioDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(dto.getIdComentario());
        comentario.setTexto(dto.getTexto());
        comentario.setData(dto.getData());
        comentario.setPostagem(postagemRepository.findById(dto.getPostagemId())
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com ID: " + dto.getPostagemId())));
        comentario.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId())));
        return comentario;
    }

    private ComentarioDTO fromEntity(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setIdComentario(comentario.getIdComentario());
        dto.setTexto(comentario.getTexto());
        dto.setData(comentario.getData());
        dto.setPostagemId(comentario.getPostagem().getIdPostagem());
        dto.setUsuarioId(comentario.getUsuario().getIdUsuario());
        return dto;
    }
}
