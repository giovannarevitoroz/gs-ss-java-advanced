package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.AlertaDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Alerta;
import com.fiap.gs_swift_safety.model.Sensor;
import com.fiap.gs_swift_safety.model.TipoDesastre;
import com.fiap.gs_swift_safety.repository.AlertaRepository;
import com.fiap.gs_swift_safety.repository.SensorRepository;
import com.fiap.gs_swift_safety.repository.TipoDesastreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final SensorRepository sensorRepository;
    private final TipoDesastreRepository tipoDesastreRepository;

    public AlertaService(AlertaRepository alertaRepository, SensorRepository sensorRepository, TipoDesastreRepository tipoDesastreRepository) {
        this.alertaRepository = alertaRepository;
        this.sensorRepository = sensorRepository;
        this.tipoDesastreRepository = tipoDesastreRepository;
    }

    // Listar todos
    public List<AlertaDTO> listarTodos() {
        return alertaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public AlertaDTO buscarPorId(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com id: " + id));
        return toDTO(alerta);
    }

    // Buscar por sensor
    public List<AlertaDTO> buscarPorSensor(Long sensorId) {
        return alertaRepository.findBySensorIdSensor(sensorId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por status
    public List<AlertaDTO> buscarPorStatus(String status) {
        return alertaRepository.findByStatusAlerta(status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por nível de risco
    public List<AlertaDTO> buscarPorNivelRisco(String nivel) {
        return alertaRepository.findByNivelRisco(nivel)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    // Buscar por data específica
    public List<AlertaDTO> buscarPorData(LocalDate data) {
        return alertaRepository.findByDataAlerta(data)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por intervalo de datas
    public List<AlertaDTO> buscarPorIntervaloDatas(LocalDate inicio, LocalDate fim) {
        return alertaRepository.findByDataAlertaBetween(inicio, fim)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por descrição parcial
    public List<AlertaDTO> buscarPorDescricao(String termo) {
        return alertaRepository.searchByDescricao(termo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por risco parcial
    public List<AlertaDTO> buscarPorRiscoParcial(String termo) {
        return alertaRepository.searchByNivelRisco(termo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Criar alerta
    public AlertaDTO salvar(AlertaDTO dto) {
        Sensor sensor = sensorRepository.findById(dto.getSensorId())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + dto.getSensorId()));

        TipoDesastre tipoDesastre = tipoDesastreRepository.findById(dto.getTipoDesastreId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de desastre não encontrado com id: " + dto.getTipoDesastreId()));

        Alerta alerta = toEntity(dto);
        alerta.setSensor(sensor);
        alerta.setTipoDesastre(tipoDesastre);

        return toDTO(alertaRepository.save(alerta));
    }

    // Deletar alerta
    public void deletar(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com id: " + id));
        alertaRepository.delete(alerta);
    }

    // Conversão Entity -> DTO
    private AlertaDTO toDTO(Alerta alerta) {
        AlertaDTO dto = new AlertaDTO();
        dto.setId(alerta.getId());
        dto.setNivelRisco(alerta.getNivelRisco());
        dto.setDataAlerta(alerta.getDataAlerta());
        dto.setDescricaoAlerta(alerta.getDescricaoAlerta());
        dto.setStatusAlerta(alerta.getStatusAlerta());
        dto.setSensorId(alerta.getSensor().getIdSensor());
        dto.setTipoDesastreId(alerta.getTipoDesastre().getIdDesastre());
        return dto;
    }

    // Conversão DTO -> Entity
    private Alerta toEntity(AlertaDTO dto) {
        Alerta alerta = new Alerta();
        alerta.setId(dto.getId());
        alerta.setNivelRisco(dto.getNivelRisco());
        alerta.setDataAlerta(dto.getDataAlerta());
        alerta.setDescricaoAlerta(dto.getDescricaoAlerta());
        alerta.setStatusAlerta(dto.getStatusAlerta());
        return alerta;
    }
}
