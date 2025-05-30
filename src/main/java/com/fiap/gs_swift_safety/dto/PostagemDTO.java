package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Schema(description = "DTO que representa uma postagem sobre um evento ou desastre")
public class PostagemDTO extends RepresentationModel {

    @Schema(description = "ID único da postagem", example = "1")
    private Long idPostagem;

    @NotBlank
    @Schema(description = "Título da postagem", example = "Deslizamento de terra em andamento")
    private String tituloPostagem;

    @NotBlank
    @Schema(description = "Descrição da postagem", example = "Moradores da Rua A relatam deslizamento após chuva intensa")
    private String descricaoPostagem;

    @NotNull
    @Schema(description = "Data da postagem", example = "2024-10-29")
    private Date dataPostagem;

    @NotBlank
    @Schema(description = "Tipo da postagem (ex: Alerta, Informação, Recomendação)", example = "Alerta")
    private String tipoPostagem;

    @NotBlank
    @Schema(description = "Status da postagem (ex: Ativo, Resolvido)", example = "Ativo")
    private String statusPostagem;

    @Schema(description = "ID do usuário que realizou a postagem", example = "5")
    private Long usuarioId;

    @Schema(description = "ID do endereço associado à postagem", example = "10")
    private Long enderecoId;

    @Schema(description = "ID do tipo de desastre associado à postagem", example = "2")
    private Long tipoDesastreId;

    // Getters e setters


    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public @NotNull Date getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(@NotNull Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public @NotBlank String getDescricaoPostagem() {
        return descricaoPostagem;
    }

    public void setDescricaoPostagem(@NotBlank String descricaoPostagem) {
        this.descricaoPostagem = descricaoPostagem;
    }

    public @NotBlank String getTituloPostagem() {
        return tituloPostagem;
    }

    public void setTituloPostagem(@NotBlank String tituloPostagem) {
        this.tituloPostagem = tituloPostagem;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public @NotBlank String getTipoPostagem() {
        return tipoPostagem;
    }

    public void setTipoPostagem(@NotBlank String tipoPostagem) {
        this.tipoPostagem = tipoPostagem;
    }

    public @NotBlank String getStatusPostagem() {
        return statusPostagem;
    }

    public void setStatusPostagem(@NotBlank String statusPostagem) {
        this.statusPostagem = statusPostagem;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getTipoDesastreId() {
        return tipoDesastreId;
    }

    public void setTipoDesastreId(Long tipoDesastreId) {
        this.tipoDesastreId = tipoDesastreId;
    }
}
