package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    @NotBlank
    @Column(name = "logradouro_endereco", nullable = false, length = 255)
    private String logradouro;

    @NotBlank
    @Column(name = "numero_endereco", nullable = false, length = 10)
    private String numero;

    @Column(name = "complemento_endereco", length = 255)
    private String complemento;

    @NotBlank
    @Column(name = "cep_endereco", nullable = false, length = 9)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "bairro_id_bairro", nullable = false)
    private Bairro bairro;

    // Getters e Setters

    public Long getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Long idEndereco) { this.idEndereco = idEndereco; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public Bairro getBairro() { return bairro; }
    public void setBairro(Bairro bairro) { this.bairro = bairro; }
}
