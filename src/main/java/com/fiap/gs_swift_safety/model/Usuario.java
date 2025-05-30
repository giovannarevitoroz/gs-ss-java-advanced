package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_usuario"),
        @UniqueConstraint(columnNames = "telefone_usuario")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank
    @Column(name = "nome_usuario", nullable = false, length = 100)
    private String nomeUsuario;

    @Email
    @Column(name = "email_usuario")
    private String emailUsuario;

    @NotBlank
    @Column(name = "senha_usuario", nullable = false, length = 50)
    private String senhaUsuario;

    @NotBlank
    @Column(name = "telefone_usuario", nullable = false, length = 11)
    private String telefoneUsuario;

    @NotBlank
    @Column(name = "tipo_usuario", nullable = false, length = 50)
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
