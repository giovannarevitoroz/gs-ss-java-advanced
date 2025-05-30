package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "TIPO_DESASTRE")
public class TipoDesastre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desastre")
    private Long idDesastre;

    @NotBlank
    @Column(name = "nome_desastre", nullable = false, length = 100)
    private String nomeDesastre;

    @NotBlank
    @Column(name = "descricao_desastre", nullable = false, length = 255)
    private String descricaoDesastre;

    // Getters e Setters
    public Long getIdDesastre() { return idDesastre; }
    public void setIdDesastre(Long idDesastre) { this.idDesastre = idDesastre; }

    public String getNomeDesastre() { return nomeDesastre; }
    public void setNomeDesastre(String nomeDesastre) { this.nomeDesastre = nomeDesastre; }

    public String getDescricaoDesastre() { return descricaoDesastre; }
    public void setDescricaoDesastre(String descricaoDesastre) { this.descricaoDesastre = descricaoDesastre; }
}

