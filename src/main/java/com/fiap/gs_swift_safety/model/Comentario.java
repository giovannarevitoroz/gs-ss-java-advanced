package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @NotBlank
    @Column(name = "texto_comentario", nullable = false, length = 255)
    private String texto;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_comentario")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "postagem_id_postagem", nullable = false)
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario", nullable = false)
    private Usuario usuario;

    // Getters e Setters

    public Long getIdComentario() { return idComentario; }
    public void setIdComentario(Long idComentario) { this.idComentario = idComentario; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public Postagem getPostagem() { return postagem; }
    public void setPostagem(Postagem postagem) { this.postagem = postagem; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}

