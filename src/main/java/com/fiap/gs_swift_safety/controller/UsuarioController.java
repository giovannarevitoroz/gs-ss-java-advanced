package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.UsuarioDTO;
import com.fiap.gs_swift_safety.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Lista todos os usuários")
    @GetMapping
    public List<UsuarioDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Lista usuários paginados")
    @GetMapping("/paginated")
    public Page<UsuarioDTO> findAllPaginated(@ParameterObject Pageable pageable) {
        return service.findAllPaginated(pageable);
    }

    @Operation(summary = "Busca usuário por ID")
    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Busca usuário por e-mail")
    @GetMapping("/email/{email}")
    public UsuarioDTO findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @Operation(summary = "Busca usuários por nome (paginado)")
    @GetMapping("/search")
    public Page<UsuarioDTO> findByNome(
            @RequestParam String nome,
            @ParameterObject Pageable pageable
    ) {
        return service.findByNomeUsuario(nome, pageable);
    }

    @Operation(summary = "Lista todos os usuários ordenados ASC")
    @GetMapping("/ordenado/asc")
    public List<UsuarioDTO> findAllOrderByNomeAsc() {
        return service.findAllOrderByNomeAsc();
    }

    @Operation(summary = "Lista todos os usuários ordenados DESC")
    @GetMapping("/ordenado/desc")
    public List<UsuarioDTO> findAllOrderByNomeDesc() {
        return service.findAllOrderByNomeDesc();
    }

    @Operation(summary = "Cria um novo usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO create(@RequestBody @Valid UsuarioDTO dto) {
        return service.create(dto);
    }

    @Operation(summary = "Atualiza um usuário existente")
    @PutMapping("/{id}")
    public UsuarioDTO update(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Remove um usuário pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
