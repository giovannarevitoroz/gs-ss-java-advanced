package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO que representa um usuário do sistema")
public class UsuarioDTO extends RepresentationModel {

    @Schema(description = "ID único do usuário", example = "1")
    private Long idUsuario;

    @NotBlank
    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    private String nomeUsuario;

    @Email
    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
    private String emailUsuario;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "senhaSegura123")
    private String senhaUsuario;

    @NotBlank
    @Schema(description = "Telefone do usuário (apenas números)", example = "11999998888")
    private String telefoneUsuario;

    @NotBlank
    @Schema(description = "Tipo do usuário (ex: 'Admin', 'Voluntário', 'Morador')", example = "Morador")
    private String tipoUsuario;

    // Getters e setters


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotBlank String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(@NotBlank String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public @Email String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(@Email String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public @NotBlank String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(@NotBlank String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public @NotBlank String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(@NotBlank String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public @NotBlank String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(@NotBlank String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
