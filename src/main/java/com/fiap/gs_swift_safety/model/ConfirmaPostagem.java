package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CONFIRMA_POSTAGEM")
public class ConfirmaPostagem implements Serializable {

    @EmbeddedId
    private ConfirmaPostagemId id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_confirma")
    private Date dataConfirma;

    @MapsId("usuarioId")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @MapsId("postagemId")
    @ManyToOne
    @JoinColumn(name = "id_postagem")
    private Postagem postagem;

    // Getters e Setters

    public ConfirmaPostagemId getId() { return id; }
    public void setId(ConfirmaPostagemId id) { this.id = id; }

    public Date getDataConfirma() { return dataConfirma; }
    public void setDataConfirma(Date dataConfirma) { this.dataConfirma = dataConfirma; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Postagem getPostagem() { return postagem; }
    public void setPostagem(Postagem postagem) { this.postagem = postagem; }
}
