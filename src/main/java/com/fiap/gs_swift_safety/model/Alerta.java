package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "ALERTA")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nivel_risco", nullable = false)
    private String nivelRisco;

    @NotNull
    @Column(name = "data_alerta", nullable = false)
    private LocalDate dataAlerta;

    @Size(max = 255)
    @Column(name = "descricao_alerta")
    private String descricaoAlerta;

    @NotBlank
    @Size(max = 50)
    @Column(name = "status_alerta", nullable = false)
    private String statusAlerta = "Ativo";

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor_id_sensor", nullable = false)
    private Sensor sensor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_desastre_id_desastre", nullable = false)
    private TipoDesastre tipoDesastre;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    public LocalDate getDataAlerta() {
        return dataAlerta;
    }

    public void setDataAlerta(LocalDate dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public String getDescricaoAlerta() {
        return descricaoAlerta;
    }

    public void setDescricaoAlerta(String descricaoAlerta) {
        this.descricaoAlerta = descricaoAlerta;
    }

    public String getStatusAlerta() {
        return statusAlerta;
    }

    public void setStatusAlerta(String statusAlerta) {
        this.statusAlerta = statusAlerta;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public TipoDesastre getTipoDesastre() {
        return tipoDesastre;
    }

    public void setTipoDesastre(TipoDesastre tipoDesastre) {
        this.tipoDesastre = tipoDesastre;
    }
}
