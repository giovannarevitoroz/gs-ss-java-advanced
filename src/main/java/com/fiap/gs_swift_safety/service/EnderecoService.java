package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.EnderecoDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Bairro;
import com.fiap.gs_swift_safety.model.Endereco;
import com.fiap.gs_swift_safety.repository.BairroRepository;
import com.fiap.gs_swift_safety.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final BairroRepository bairroRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, BairroRepository bairroRepository) {
        this.enderecoRepository = enderecoRepository;
        this.bairroRepository = bairroRepository;
    }

    public List<EnderecoDTO> findAll() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO findById(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
        return toDTO(endereco);
    }

    public EnderecoDTO create(EnderecoDTO dto) {
        Endereco endereco = toEntity(dto);
        Endereco saved = enderecoRepository.save(endereco);
        return toDTO(saved);
    }

    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        Endereco existing = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
        Endereco updated = toEntity(dto);
        updated.setIdEndereco(existing.getIdEndereco());
        return toDTO(enderecoRepository.save(updated));
    }

    public void delete(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
        enderecoRepository.delete(endereco);
    }

    public List<EnderecoDTO> findByCep(String cep) {
        return enderecoRepository.findByCep(cep)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> findByLogradouroContaining(String logradouro) {
        return enderecoRepository.findByLogradouroContainingIgnoreCase(logradouro)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> findByBairroNome(String nome) {
        return enderecoRepository.findByBairroNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> findByNomeExatoDoBairro(String nome) {
        return enderecoRepository.findByNomeExatoDoBairro(nome)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> findByBairroId(Long bairroId) {
        return enderecoRepository.findByBairroId(bairroId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private EnderecoDTO toDTO(Endereco e) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setIdEndereco(e.getIdEndereco());
        dto.setLogradouro(e.getLogradouro());
        dto.setNumero(e.getNumero());
        dto.setComplemento(e.getComplemento());
        dto.setCep(e.getCep());
        dto.setBairroId(e.getBairro().getId());
        return dto;
    }

    private Endereco toEntity(EnderecoDTO dto) {
        Bairro bairro = bairroRepository.findById(dto.getBairroId())
                .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com ID: " + dto.getBairroId()));

        Endereco e = new Endereco();
        e.setLogradouro(dto.getLogradouro());
        e.setNumero(dto.getNumero());
        e.setComplemento(dto.getComplemento());
        e.setCep(dto.getCep());
        e.setBairro(bairro);
        return e;
    }
}
