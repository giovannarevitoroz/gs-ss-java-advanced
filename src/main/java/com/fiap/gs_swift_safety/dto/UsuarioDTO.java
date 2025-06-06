package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO que representa um usuário do sistema")
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {
    @Schema(description = "ID único do usuário", example = "1")
    private Long idUsuario;

    @NotBlank
    @Schema(description = "Nome completo do usuário", example = "João da Silva", required = true)
    private String nomeUsuario;

    @NotBlank @Email
    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com", required = true)
    private String emailUsuario;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "senhaSegura123", required = true)
    private String senhaUsuario;

    @NotBlank
    @Schema(description = "Telefone do usuário", example = "11999998888", required = true)
    private String telefoneUsuario;

    @NotBlank
    @Pattern(regexp = "Usuário|Funcionário",
            message = "Tipo de usuário deve ser 'Usuário' ou 'Funcionário'")
    @Schema(description = "Tipo do usuário", example = "Usuário", allowableValues = {"Usuário", "Funcionário"}, required = true)
    private String tipoUsuario;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
