package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.TipoDesastreDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.TipoDesastre;
import com.fiap.gs_swift_safety.repository.TipoDesastreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDesastreService {

    @Autowired
    private TipoDesastreRepository repository;

    // Converter Entity para DTO
    private TipoDesastreDTO toDTO(TipoDesastre tipoDesastre) {
        TipoDesastreDTO dto = new TipoDesastreDTO();
        dto.setIdDesastre(tipoDesastre.getIdDesastre());
        dto.setNomeDesastre(tipoDesastre.getNomeDesastre());
        dto.setDescricaoDesastre(tipoDesastre.getDescricaoDesastre());
        return dto;
    }

    // Converter DTO para Entity
    private TipoDesastre toEntity(TipoDesastreDTO dto) {
        TipoDesastre tipoDesastre = new TipoDesastre();
        tipoDesastre.setNomeDesastre(dto.getNomeDesastre());
        tipoDesastre.setDescricaoDesastre(dto.getDescricaoDesastre());
        return tipoDesastre;
    }

    // CRUD Operations
    public List<TipoDesastreDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Page<TipoDesastreDTO> findAllPaginated(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDTO);
    }

    public TipoDesastreDTO findById(Long id) {
        TipoDesastre tipoDesastre = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de desastre não encontrado com ID: " + id));
        return toDTO(tipoDesastre);
    }

    public TipoDesastreDTO create(TipoDesastreDTO dto) {
        TipoDesastre tipoDesastre = toEntity(dto);
        tipoDesastre = repository.save(tipoDesastre);
        return toDTO(tipoDesastre);
    }

    public TipoDesastreDTO update(Long id, TipoDesastreDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de desastre não encontrado com ID: " + id);
        }
        TipoDesastre tipoDesastre = toEntity(dto);
        tipoDesastre.setIdDesastre(id);
        tipoDesastre = repository.save(tipoDesastre);
        return toDTO(tipoDesastre);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de desastre não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Custom Queries
    public List<TipoDesastreDTO> findByNomeDesastre(String nome) {
        return repository.findByNomeDesastre(nome).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TipoDesastreDTO> findByDescricaoDesastre(String descricao) {
        return repository.findByDescricaoDesastre(descricao).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TipoDesastreDTO> findAllOrderByNomeAsc() {
        return repository.findAllByOrderByNomeDesastreAsc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TipoDesastreDTO> findAllOrderByNomeDesc() {
        return repository.findAllByOrderByNomeDesastreDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}