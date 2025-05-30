package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.BairroDTO;
import com.fiap.gs_swift_safety.service.BairroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairros")
public class BairroController {

    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @PostMapping
    public ResponseEntity<BairroDTO> criar(@RequestBody @Valid BairroDTO dto) {
        return ResponseEntity.ok(bairroService.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BairroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bairroService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<BairroDTO>> buscarTodos() {
        return ResponseEntity.ok(bairroService.buscarTodos());
    }

    @GetMapping("/nome-exato/{nome}")
    public ResponseEntity<BairroDTO> buscarPorNomeExato(@PathVariable String nome) {
        return ResponseEntity.ok(bairroService.buscarPorNomeExato(nome));
    }

    @GetMapping("/nome-contendo/{nome}")
    public ResponseEntity<List<BairroDTO>> buscarPorNomeContendo(@PathVariable String nome) {
        return ResponseEntity.ok(bairroService.buscarPorNomeContendo(nome));
    }

    @GetMapping("/zona/{zona}")
    public ResponseEntity<List<BairroDTO>> buscarPorZona(@PathVariable String zona) {
        return ResponseEntity.ok(bairroService.buscarPorZona(zona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BairroDTO> atualizar(@PathVariable Long id, @RequestBody @Valid BairroDTO dto) {
        return ResponseEntity.ok(bairroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bairroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
