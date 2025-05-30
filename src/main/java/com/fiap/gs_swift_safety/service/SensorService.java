package com.fiap.gs_swift_safety.service;

import com.fiap.gs_swift_safety.dto.SensorDTO;
import com.fiap.gs_swift_safety.exception.ResourceNotFoundException;
import com.fiap.gs_swift_safety.model.Sensor;
import com.fiap.gs_swift_safety.repository.BairroRepository;
import com.fiap.gs_swift_safety.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    @Autowired
    private SensorRepository repository;

    @Autowired
    private BairroRepository bairroRepository;

    // Converter Entity para DTO
    private SensorDTO toDTO(Sensor sensor) {
        SensorDTO dto = new SensorDTO();
        dto.setIdSensor(sensor.getIdSensor());
        dto.setNomeSensor(sensor.getNomeSensor());
        dto.setTipoSensor(sensor.getTipoSensor());
        if (sensor.getBairro() != null) {
            dto.setBairroId(sensor.getBairro().getId());
        }
        return dto;
    }

    // Converter DTO para Entity, buscando Bairro pelo id e setando no Sensor
    private Sensor toEntity(SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setNomeSensor(dto.getNomeSensor());
        sensor.setTipoSensor(dto.getTipoSensor());

        if (dto.getBairroId() != null) {
            sensor.setBairro(bairroRepository.findById(dto.getBairroId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com ID: " + dto.getBairroId())));
        }
        return sensor;
    }

    // CRUD Operations
    public List<SensorDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Page<SensorDTO> findAllPaginated(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDTO);
    }

    public SensorDTO findById(Long id) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com ID: " + id));
        return toDTO(sensor);
    }

    public SensorDTO create(SensorDTO dto) {
        Sensor sensor = toEntity(dto);
        sensor = repository.save(sensor);
        return toDTO(sensor);
    }

    public SensorDTO update(Long id, SensorDTO dto) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com ID: " + id));

        sensor.setNomeSensor(dto.getNomeSensor());
        sensor.setTipoSensor(dto.getTipoSensor());

        if (dto.getBairroId() != null) {
            sensor.setBairro(bairroRepository.findById(dto.getBairroId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado com ID: " + dto.getBairroId())));
        }

        sensor = repository.save(sensor);
        return toDTO(sensor);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Sensor não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Custom Queries
    public List<SensorDTO> findByNomeSensor(String nome) {
        return repository.findByNomeSensorIgnoreCase(nome).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<SensorDTO> findByTipoSensor(String tipo) {
        return repository.findByTipoSensor(tipo).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<SensorDTO> buscarPorNomeEPorTipo(String nome, String tipo) {
        return repository.buscarPorNomeEPorTipoJPQL(nome, tipo).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
