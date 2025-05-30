package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO representando a chave composta de Confirmação de Postagem")
public class ConfirmaPostagemIdDTO extends RepresentationModel {

    @Schema(description = "ID do usuário que confirmou a postagem", example = "1")
    private Long usuarioId;

    @Schema(description = "ID da postagem confirmada pelo usuário", example = "100")
    private Long postagemId;

    public ConfirmaPostagemIdDTO() {}

    public ConfirmaPostagemIdDTO(Long usuarioId, Long postagemId) {
        this.usuarioId = usuarioId;
        this.postagemId = postagemId;
    }

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
}
