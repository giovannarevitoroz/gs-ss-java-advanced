package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.AlertaDTO;
import com.fiap.gs_swift_safety.service.AlertaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    // GET - Listar todos os alertas
    @GetMapping
    public ResponseEntity<List<AlertaDTO>> listarTodos() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }

    // GET - Buscar alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.buscarPorId(id));
    }

    // GET - Buscar por sensor
    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<AlertaDTO>> buscarPorSensor(@PathVariable Long sensorId) {
        return ResponseEntity.ok(alertaService.buscarPorSensor(sensorId));
    }

    // GET - Buscar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<AlertaDTO>> buscarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(alertaService.buscarPorStatus(status));
    }

    // GET - Buscar por nível de risco
    @GetMapping("/nivel-risco/{nivel}")
    public ResponseEntity<List<AlertaDTO>> buscarPorNivelRisco(@PathVariable String nivel) {
        return ResponseEntity.ok(alertaService.buscarPorNivelRisco(nivel));
    }

    // GET - Buscar por data específica
    @GetMapping("/data/{data}")
    public ResponseEntity<List<AlertaDTO>> buscarPorData(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(alertaService.buscarPorData(data));
    }

    // GET - Buscar por intervalo de datas
    @GetMapping("/intervalo")
    public ResponseEntity<List<AlertaDTO>> buscarPorIntervaloDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(alertaService.buscarPorIntervaloDatas(inicio, fim));
    }

    // GET - Buscar por descrição parcial
    @GetMapping("/descricao")
    public ResponseEntity<List<AlertaDTO>> buscarPorDescricao(@RequestParam String termo) {
        return ResponseEntity.ok(alertaService.buscarPorDescricao(termo));
    }

    // GET - Buscar por risco parcial
    @GetMapping("/risco")
    public ResponseEntity<List<AlertaDTO>> buscarPorRiscoParcial(@RequestParam String termo) {
        return ResponseEntity.ok(alertaService.buscarPorRiscoParcial(termo));
    }

    // POST - Criar novo alerta
    @PostMapping
    public ResponseEntity<AlertaDTO> salvar(@RequestBody AlertaDTO dto) {
        return ResponseEntity.ok(alertaService.salvar(dto));
    }

    // DELETE - Remover alerta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
