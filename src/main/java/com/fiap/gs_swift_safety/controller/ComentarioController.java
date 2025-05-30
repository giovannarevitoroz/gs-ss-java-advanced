package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.ComentarioDTO;
import com.fiap.gs_swift_safety.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> criar(@RequestBody @Valid ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> buscarTodos() {
        return ResponseEntity.ok(comentarioService.buscarTodos());
    }

    @GetMapping("/por-postagem/{postagemId}")
    public ResponseEntity<List<ComentarioDTO>> buscarPorPostagem(@PathVariable Long postagemId) {
        return ResponseEntity.ok(comentarioService.buscarPorPostagem(postagemId));
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comentarioService.buscarPorUsuario(usuarioId));
    }

    @GetMapping("/ordenado-data/{postagemId}")
    public ResponseEntity<List<ComentarioDTO>> buscarPorPostagemOrdenado(@PathVariable Long postagemId) {
        return ResponseEntity.ok(comentarioService.buscarPorPostagemOrdenadoPorData(postagemId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comentarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
