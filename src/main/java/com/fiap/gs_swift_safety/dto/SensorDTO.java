package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO que representa um sensor instalado")
public class SensorDTO extends RepresentationModel {

    @Schema(description = "ID único do sensor", example = "1")
    private Long idSensor;

    @NotBlank
    @Schema(description = "Nome do sensor", example = "Sensor de Enchente 01")
    private String nomeSensor;

    @NotBlank
    @Schema(description = "Tipo do sensor (ex: temperatura, água, fumaça)", example = "Nível de Água")
    private String tipoSensor;

    @Schema(description = "ID do bairro onde o sensor está localizado", example = "3")
    private Long bairroId;

    // Getters e setters

    public Long getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(Long idSensor) {
        this.idSensor = idSensor;
    }

    public @NotBlank String getNomeSensor() {
        return nomeSensor;
    }

    public void setNomeSensor(@NotBlank String nomeSensor) {
        this.nomeSensor = nomeSensor;
    }

    public Long getBairroId() {
        return bairroId;
    }

    public void setBairroId(Long bairroId) {
        this.bairroId = bairroId;
    }

    public @NotBlank String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(@NotBlank String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }
}
