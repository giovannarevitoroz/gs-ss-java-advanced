package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.PostagemDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.*;
import com.fiap.gs_swift_safety.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostagemService {

    @Autowired private PostagemRepository postagemRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private TipoDesastreRepository tipoDesastreRepository;

    public List<PostagemDTO> findAll() {
        return postagemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PostagemDTO findById(Long id) {
        return toDTO(postagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com id: " + id)));
    }

    public PostagemDTO save(PostagemDTO dto) {
        Postagem postagem = toEntity(dto);
        return toDTO(postagemRepository.save(postagem));
    }

    public PostagemDTO update(Long id, PostagemDTO dto) {
        Postagem existente = postagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com id: " + id));
        dto.setIdPostagem(id);
        Postagem atualizado = toEntity(dto);
        atualizado.setIdPostagem(existente.getIdPostagem());
        return toDTO(postagemRepository.save(atualizado));
    }

    public void delete(Long id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada com id: " + id));
        postagemRepository.delete(postagem);
    }

    public List<PostagemDTO> findByTitulo(String titulo) {
        return postagemRepository.findByTituloPostagemIgnoreCase(titulo)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findByDataRange(Date inicio, Date fim) {
        return postagemRepository.findPostagensPorDatas(inicio, fim)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findByTipo(String tipo) {
        return postagemRepository.findPostagensPorTipo(tipo)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findByStatus(String status) {
        return postagemRepository.findPostagensPorStatus(status)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findByUsuario(Long usuarioId) {
        return postagemRepository.findPostagensPorUsuario(usuarioId)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findByEndereco(Long enderecoId) {
        return postagemRepository.findPostagensPorEndereco(enderecoId)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findByTipoDesastre(Long tipoDesastreId) {
        return postagemRepository.findPostagensPorTipoDesastre(tipoDesastreId)
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findAllOrderByTituloAsc() {
        return postagemRepository.findAllByOrderByTituloPostagemAsc()
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findAllOrderByDataDesc() {
        return postagemRepository.findAllByOrderByDataPostagemDesc()
                .stream().map(this::toDTO).toList();
    }

    public List<PostagemDTO> findAllOrderByDataAsc() {
        return postagemRepository.findAllByOrderByDataPostagemAsc()
                .stream().map(this::toDTO).toList();
    }

    // Conversão Entity -> DTO
    private PostagemDTO toDTO(Postagem p) {
        PostagemDTO dto = new PostagemDTO();
        dto.setIdPostagem(p.getIdPostagem());
        dto.setTituloPostagem(p.getTituloPostagem());
        dto.setDescricaoPostagem(p.getDescricaoPostagem());
        dto.setDataPostagem(p.getDataPostagem());
        dto.setTipoPostagem(p.getTipoPostagem());
        dto.setStatusPostagem(p.getStatusPostagem());
        dto.setUsuarioId(p.getUsuario().getIdUsuario());
        dto.setEnderecoId(p.getEndereco().getIdEndereco());
        dto.setTipoDesastreId(p.getTipoDesastre().getIdDesastre());
        return dto;
    }

    // Conversão DTO -> Entity
    private Postagem toEntity(PostagemDTO dto) {
        Postagem p = new Postagem();
        p.setIdPostagem(dto.getIdPostagem());
        p.setTituloPostagem(dto.getTituloPostagem());
        p.setDescricaoPostagem(dto.getDescricaoPostagem());
        p.setDataPostagem(dto.getDataPostagem());
        p.setTipoPostagem(dto.getTipoPostagem());
        p.setStatusPostagem(dto.getStatusPostagem());

        Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + dto.getUsuarioId()));
        Endereco e = enderecoRepository.findById(dto.getEnderecoId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com id: " + dto.getEnderecoId()));
        TipoDesastre td = tipoDesastreRepository.findById(dto.getTipoDesastreId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de desastre não encontrado com id: " + dto.getTipoDesastreId()));

        p.setUsuario(u);
        p.setEndereco(e);
        p.setTipoDesastre(td);
        return p;
    }
}
