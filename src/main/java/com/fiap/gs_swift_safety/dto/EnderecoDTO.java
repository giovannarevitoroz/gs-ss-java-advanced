package com.fiap.gs_swift_safety.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "DTO que representa um endereço")
public class EnderecoDTO extends RepresentationModel {

    @Schema(description = "ID único do endereço", example = "1")
    private Long idEndereco;

    @NotBlank
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @NotBlank
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apartamento 21")
    private String complemento;

    @NotBlank
    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

    @Schema(description = "ID do bairro relacionado", example = "7")
    private Long bairroId;

    // Getters e setters

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public @NotBlank String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(@NotBlank String logradouro) {
        this.logradouro = logradouro;
    }

    public @NotBlank String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public @NotBlank String getCep() {
        return cep;
    }

    public void setCep(@NotBlank String cep) {
        this.cep = cep;
    }

    public Long getBairroId() {
        return bairroId;
    }

    public void setBairroId(Long bairroId) {
        this.bairroId = bairroId;
    }
}
