package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.BairroDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Bairro;
import com.fiap.gs_swift_safety.repository.BairroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {

    private final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro = toEntity(dto);
        return fromEntity(bairroRepository.save(bairro));
    }

    public BairroDTO buscarPorId(Long id) {
        Bairro bairro = bairroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com ID: " + id));
        return fromEntity(bairro);
    }

    public List<BairroDTO> buscarTodos() {
        return bairroRepository.findAll().stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public BairroDTO buscarPorNomeExato(String nomeBairro) {
        Bairro bairro = bairroRepository.findByNomeBairro(nomeBairro)
                .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com nome: " + nomeBairro));
        return fromEntity(bairro);
    }

    public List<BairroDTO> buscarPorNomeContendo(String nomeParcial) {
        return bairroRepository.findByNomeBairroContainingIgnoreCase(nomeParcial).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BairroDTO> buscarPorZona(String zonaBairro) {
        return bairroRepository.findByZonaBairro(zonaBairro).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        Bairro bairro = bairroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com ID: " + id));
        bairroRepository.delete(bairro);
    }

    public BairroDTO atualizar(Long id, BairroDTO dto) {
        Bairro bairro = bairroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com ID: " + id));

        bairro.setNomeBairro(dto.getNomeBairro());
        bairro.setZonaBairro(dto.getZonaBairro());

        return fromEntity(bairroRepository.save(bairro));
    }

    private Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();
        bairro.setId(dto.getId());
        bairro.setNomeBairro(dto.getNomeBairro());
        bairro.setZonaBairro(dto.getZonaBairro());
        return bairro;
    }

    private BairroDTO fromEntity(Bairro bairro) {
        BairroDTO dto = new BairroDTO();
        dto.setId(bairro.getId());
        dto.setNomeBairro(bairro.getNomeBairro());
        dto.setZonaBairro(bairro.getZonaBairro());
        return dto;
    }
}
