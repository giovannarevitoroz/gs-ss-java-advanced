package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.ConfirmaPostagemDTO;
import com.fiap.gs_swift_safety.service.ConfirmaPostagemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/confirma-postagem")
public class ConfirmaPostagemController {

    private final ConfirmaPostagemService service;

    public ConfirmaPostagemController(ConfirmaPostagemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConfirmaPostagemDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{usuarioId}/{postagemId}")
    public ResponseEntity<ConfirmaPostagemDTO> getById(
            @PathVariable Long usuarioId,
            @PathVariable Long postagemId
    ) {
        return ResponseEntity.ok(service.findById(usuarioId, postagemId));
    }

    @PostMapping
    public ResponseEntity<ConfirmaPostagemDTO> create(@RequestBody @Valid ConfirmaPostagemDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @DeleteMapping("/{usuarioId}/{postagemId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long usuarioId,
            @PathVariable Long postagemId
    ) {
        service.delete(usuarioId, postagemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ConfirmaPostagemDTO>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.findByUsuario(usuarioId));
    }

    @GetMapping("/postagem/{postagemId}")
    public ResponseEntity<List<ConfirmaPostagemDTO>> getByPostagem(@PathVariable Long postagemId) {
        return ResponseEntity.ok(service.findByPostagem(postagemId));
    }
}
