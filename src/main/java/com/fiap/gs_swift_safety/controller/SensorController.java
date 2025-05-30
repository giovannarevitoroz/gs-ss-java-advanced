package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.SensorDTO;
import com.fiap.gs_swift_safety.service.SensorService;
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
@RequestMapping("/api/sensores")
@Tag(name = "Sensores", description = "API para gerenciamento de sensores")
public class SensorController {

    @Autowired
    private SensorService service;

    @GetMapping
    @Operation(summary = "Listar todos os sensores")
    public ResponseEntity<List<SensorDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar sensores com paginação")
    public ResponseEntity<Page<SensorDTO>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPaginated(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sensor por ID")
    public ResponseEntity<SensorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar um novo sensor")
    public ResponseEntity<SensorDTO> create(@RequestBody SensorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um sensor existente")
    public ResponseEntity<SensorDTO> update(@PathVariable Long id, @RequestBody SensorDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um sensor")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar sensores por nome")
    public ResponseEntity<List<SensorDTO>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.findByNomeSensor(nome));
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar sensores por tipo")
    public ResponseEntity<List<SensorDTO>> findByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.findByTipoSensor(tipo));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar sensores por nome e tipo")
    public ResponseEntity<List<SensorDTO>> buscarPorNomeETipo(
            @RequestParam String nome,
            @RequestParam String tipo) {
        return ResponseEntity.ok(service.buscarPorNomeEPorTipo(nome, tipo));
    }
}