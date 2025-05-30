package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.TipoDesastreDTO;
import com.fiap.gs_swift_safety.service.TipoDesastreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-desastre")
@Tag(name = "Tipos de Desastre", description = "API para gerenciamento de tipos de desastres")
public class TipoDesastreController {

    @Autowired
    private TipoDesastreService service;

    @GetMapping
    @Operation(summary = "Listar todos os tipos de desastre")
    public ResponseEntity<List<TipoDesastreDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar tipos de desastre com paginação")
    public ResponseEntity<Page<TipoDesastreDTO>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPaginated(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de desastre por ID")
    public ResponseEntity<TipoDesastreDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar um novo tipo de desastre")
    public ResponseEntity<TipoDesastreDTO> create(@RequestBody TipoDesastreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um tipo de desastre existente")
    public ResponseEntity<TipoDesastreDTO> update(@PathVariable Long id, @RequestBody TipoDesastreDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um tipo de desastre")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar tipos de desastre por nome")
    public ResponseEntity<List<TipoDesastreDTO>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.findByNomeDesastre(nome));
    }

    @GetMapping("/descricao/{descricao}")
    @Operation(summary = "Buscar tipos de desastre por descrição exata")
    public ResponseEntity<List<TipoDesastreDTO>> findByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(service.findByDescricaoDesastre(descricao));
    }

    @GetMapping("/ordenados-asc")
    @Operation(summary = "Listar tipos de desastre ordenados por nome (A-Z)")
    public ResponseEntity<List<TipoDesastreDTO>> findAllOrderByNomeAsc() {
        return ResponseEntity.ok(service.findAllOrderByNomeAsc());
    }

    @GetMapping("/ordenados-desc")
    @Operation(summary = "Listar tipos de desastre ordenados por nome (Z-A)")
    public ResponseEntity<List<TipoDesastreDTO>> findAllOrderByNomeDesc() {
        return ResponseEntity.ok(service.findAllOrderByNomeDesc());
    }
}