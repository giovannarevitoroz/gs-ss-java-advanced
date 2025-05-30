package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Schema(description = "DTO para alerta")
public class AlertaDTO extends RepresentationModel {

    @Schema(description = "ID do alerta", example = "1")
    private Long id;

    @Schema(description = "Nível de risco do alerta", example = "Alto")
    private String nivelRisco;

    @Schema(description = "Data do alerta", example = "2024-10-01")
    private LocalDate dataAlerta;

    @Schema(description = "Descrição do alerta", example = "Chuvas intensas com risco de alagamento")
    private String descricaoAlerta;

    @Schema(description = "Status do alerta", example = "Ativo")
    private String statusAlerta;

    @Schema(description = "ID do sensor relacionado ao alerta", example = "3")
    private Long sensorId;

    @Schema(description = "ID do tipo de desastre", example = "2")
    private Long tipoDesastreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    public LocalDate getDataAlerta() {
        return dataAlerta;
    }

    public void setDataAlerta(LocalDate dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public String getDescricaoAlerta() {
        return descricaoAlerta;
    }

    public void setDescricaoAlerta(String descricaoAlerta) {
        this.descricaoAlerta = descricaoAlerta;
    }

    public String getStatusAlerta() {
        return statusAlerta;
    }

    public void setStatusAlerta(String statusAlerta) {
        this.statusAlerta = statusAlerta;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Long getTipoDesastreId() {
        return tipoDesastreId;
    }

    public void setTipoDesastreId(Long tipoDesastreId) {
        this.tipoDesastreId = tipoDesastreId;
    }
}
