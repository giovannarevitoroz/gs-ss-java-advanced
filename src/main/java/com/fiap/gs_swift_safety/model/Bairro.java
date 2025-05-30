package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "BAIRRO")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bairro")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_bairro", nullable = false, unique = true)
    private String nomeBairro;

    @Size(max = 50)
    @Column(name = "zona_bairro")
    private String zonaBairro;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public String getZonaBairro() {
        return zonaBairro;
    }

    public void setZonaBairro(String zonaBairro) {
        this.zonaBairro = zonaBairro;
    }
}
