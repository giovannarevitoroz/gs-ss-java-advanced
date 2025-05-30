package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Schema(description = "DTO para confirmação de postagem")
public class ConfirmaPostagemDTO extends RepresentationModel {

    @Schema(description = "ID do usuário que confirmou", example = "1")
    private Long usuarioId;

    @Schema(description = "ID da postagem confirmada", example = "10")
    private Long postagemId;

    @Schema(description = "Data da confirmação", example = "2024-10-01")
    private Date dataConfirma;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPostagemId() {
        return postagemId;
    }

    public void setPostagemId(Long postagemId) {
        this.postagemId = postagemId;
    }

    public Date getDataConfirma() {
        return dataConfirma;
    }

    public void setDataConfirma(Date dataConfirma) {
        this.dataConfirma = dataConfirma;
    }
}
