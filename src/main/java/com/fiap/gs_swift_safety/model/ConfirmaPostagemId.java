package com.fiap.gs_swift_safety.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConfirmaPostagemId implements Serializable {

    @Column(name = "id_usuario")
    private Long usuarioId;

    @Column(name = "id_postagem")
    private Long postagemId;

    public ConfirmaPostagemId() {}

    public ConfirmaPostagemId(Long usuarioId, Long postagemId) {
        this.usuarioId = usuarioId;
        this.postagemId = postagemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfirmaPostagemId)) return false;
        ConfirmaPostagemId that = (ConfirmaPostagemId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(postagemId, that.postagemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, postagemId);
    }

    // Getters e Setters

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPostagemId() { return postagemId; }
    public void setPostagemId(Long postagemId) { this.postagemId = postagemId; }
}
