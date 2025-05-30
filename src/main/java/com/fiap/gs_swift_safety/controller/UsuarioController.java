package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.UsuarioDTO;
import com.fiap.gs_swift_safety.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar usuários com paginação")
    public ResponseEntity<Page<UsuarioDTO>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPaginated(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar usuário por email")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário existente")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar usuários por nome")
    public ResponseEntity<Page<UsuarioDTO>> findByNome(@PathVariable String nome, Pageable pageable) {
        return ResponseEntity.ok(service.findByNomeUsuario(nome, pageable));
    }

    @GetMapping("/ordenados-asc")
    @Operation(summary = "Listar usuários ordenados por nome (A-Z)")
    public ResponseEntity<List<UsuarioDTO>> findAllOrderByNomeAsc() {
        return ResponseEntity.ok(service.findAllOrderByNomeAsc());
    }

    @GetMapping("/ordenados-desc")
    @Operation(summary = "Listar usuários ordenados por nome (Z-A)")
    public ResponseEntity<List<UsuarioDTO>> findAllOrderByNomeDesc() {
        return ResponseEntity.ok(service.findAllOrderByNomeDesc());
    }
}