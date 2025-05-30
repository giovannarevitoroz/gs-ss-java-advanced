package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "POSTAGEM")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postagem")
    private Long idPostagem;

    @NotBlank
    @Column(name = "titulo_postagem", nullable = false, length = 100)
    private String tituloPostagem;

    @NotBlank
    @Column(name = "descricao_postagem", nullable = false, length = 255)
    private String descricaoPostagem;

    @NotNull
    @Column(name = "data_postagem", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataPostagem;

    @NotBlank
    @Column(name = "tipo_postagem", nullable = false, length = 50)
    private String tipoPostagem;

    @NotBlank
    @Column(name = "status_postagem", nullable = false, length = 50)
    private String statusPostagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "endereco_id_endereco", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "tipo_desastre_id_desastre", nullable = false)
    private TipoDesastre tipoDesastre;

    // Getters e Setters
    public Long getIdPostagem() { return idPostagem; }
    public void setIdPostagem(Long idPostagem) { this.idPostagem = idPostagem; }

    public String getTituloPostagem() { return tituloPostagem; }
    public void setTituloPostagem(String tituloPostagem) { this.tituloPostagem = tituloPostagem; }

    public String getDescricaoPostagem() { return descricaoPostagem; }
    public void setDescricaoPostagem(String descricaoPostagem) { this.descricaoPostagem = descricaoPostagem; }

    public Date getDataPostagem() { return dataPostagem; }
    public void setDataPostagem(Date dataPostagem) { this.dataPostagem = dataPostagem; }

    public String getTipoPostagem() { return tipoPostagem; }
    public void setTipoPostagem(String tipoPostagem) { this.tipoPostagem = tipoPostagem; }

    public String getStatusPostagem() { return statusPostagem; }
    public void setStatusPostagem(String statusPostagem) { this.statusPostagem = statusPostagem; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public TipoDesastre getTipoDesastre() { return tipoDesastre; }
    public void setTipoDesastre(TipoDesastre tipoDesastre) { this.tipoDesastre = tipoDesastre; }
}
