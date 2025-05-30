package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.EnderecoDTO;
import com.fiap.gs_swift_safety.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Valid EnderecoDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody @Valid EnderecoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<EnderecoDTO>> getByCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.findByCep(cep));
    }

    @GetMapping("/logradouro/{logradouro}")
    public ResponseEntity<List<EnderecoDTO>> getByLogradouro(@PathVariable String logradouro) {
        return ResponseEntity.ok(service.findByLogradouroContaining(logradouro));
    }

    @GetMapping("/bairro/nome/{nome}")
    public ResponseEntity<List<EnderecoDTO>> getByBairroNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.findByBairroNome(nome));
    }

    @GetMapping("/bairro/nome-exato/{nome}")
    public ResponseEntity<List<EnderecoDTO>> getByBairroNomeExato(@PathVariable String nome) {
        return ResponseEntity.ok(service.findByNomeExatoDoBairro(nome));
    }

    @GetMapping("/bairro/id/{id}")
    public ResponseEntity<List<EnderecoDTO>> getByBairroId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByBairroId(id));
    }
}
