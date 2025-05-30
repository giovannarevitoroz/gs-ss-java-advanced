package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO para bairro")
public class BairroDTO extends RepresentationModel {

    @Schema(description = "ID do bairro", example = "1")
    private Long id;

    @Schema(description = "Nome do bairro", example = "Jardim Paulista")
    private String nomeBairro;

    @Schema(description = "Zona do bairro", example = "Zona Sul")
    private String zonaBairro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public String getZonaBairro() {
        return zonaBairro;
    }

    public void setZonaBairro(String zonaBairro) {
        this.zonaBairro = zonaBairro;
    }
}
