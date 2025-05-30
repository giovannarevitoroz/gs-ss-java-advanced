package com.fiap.gs_swift_safety.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "SENSOR", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nome_sensor")
})
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Long idSensor;

    @NotBlank
    @Column(name = "nome_sensor", nullable = false, length = 100)
    private String nomeSensor;

    @NotBlank
    @Column(name = "tipo_sensor", nullable = false, length = 50)
    private String tipoSensor;

    @ManyToOne
    @JoinColumn(name = "bairro_id_bairro", nullable = false)
    private Bairro bairro;

    // Getters e Setters
    public Long getIdSensor() { return idSensor; }
    public void setIdSensor(Long idSensor) { this.idSensor = idSensor; }

    public String getNomeSensor() { return nomeSensor; }
    public void setNomeSensor(String nomeSensor) { this.nomeSensor = nomeSensor; }

    public String getTipoSensor() { return tipoSensor; }
    public void setTipoSensor(String tipoSensor) { this.tipoSensor = tipoSensor; }

    public Bairro getBairro() { return bairro; }
    public void setBairro(Bairro bairro) { this.bairro = bairro; }
}
