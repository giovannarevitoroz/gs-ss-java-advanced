package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Schema(description = "DTO para transferência de dados de comentários")
public class ComentarioDTO extends RepresentationModel {

    @Schema(description = "ID do comentário", example = "1")
    private Long idComentario;

    @Schema(description = "Texto do comentário", example = "Muito útil essa postagem.")
    private String texto;

    @Schema(description = "Data do comentário", example = "2024-05-29")
    private Date data;

    @Schema(description = "ID da postagem relacionada", example = "10")
    private Long postagemId;

    @Schema(description = "ID do usuário que fez o comentário", example = "5")
    private Long usuarioId;

    // Getters e Setters

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getPostagemId() {
        return postagemId;
    }

    public void setPostagemId(Long postagemId) {
        this.postagemId = postagemId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
