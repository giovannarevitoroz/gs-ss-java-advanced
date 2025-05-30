package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO que representa o tipo de desastre")
public class TipoDesastreDTO extends RepresentationModel {

    @Schema(description = "ID único do tipo de desastre", example = "1")
    private Long idDesastre;

    @NotBlank
    @Schema(description = "Nome do desastre", example = "Enchente")
    private String nomeDesastre;

    @NotBlank
    @Schema(description = "Descrição do desastre", example = "Alagamento causado por fortes chuvas")
    private String descricaoDesastre;

    // Getters e setters


    public Long getIdDesastre() {
        return idDesastre;
    }

    public void setIdDesastre(Long idDesastre) {
        this.idDesastre = idDesastre;
    }

    public @NotBlank String getNomeDesastre() {
        return nomeDesastre;
    }

    public void setNomeDesastre(@NotBlank String nomeDesastre) {
        this.nomeDesastre = nomeDesastre;
    }

    public @NotBlank String getDescricaoDesastre() {
        return descricaoDesastre;
    }

    public void setDescricaoDesastre(@NotBlank String descricaoDesastre) {
        this.descricaoDesastre = descricaoDesastre;
    }
}
