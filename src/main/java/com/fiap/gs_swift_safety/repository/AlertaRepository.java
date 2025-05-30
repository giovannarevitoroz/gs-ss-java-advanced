package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // Retorna todos os alertas
    List<Alerta> findAll();

    // Busca um alerta específico pelo ID
    List<Alerta> findBySensorIdSensor(Long idSensor);

    // Retorna todos os alertas com determinado status (ex: "Ativo", "Resolvido")
    List<Alerta> findByStatusAlerta(String statusAlerta);

    // Retorna alertas com determinado nível de risco (ex: "Alto", "Moderado")
    List<Alerta> findByNivelRisco(String nivelRisco);

    // Retorna alertas registrados em uma data específica
    List<Alerta> findByDataAlerta(LocalDate dataAlerta);


    // Busca alertas por nível de risco com correspondência parcial (case-insensitive)
    @Query("SELECT a FROM Alerta a WHERE LOWER(a.nivelRisco) LIKE LOWER(CONCAT('%', :nivel, '%'))")
    List<Alerta> searchByNivelRisco(@Param("nivel") String nivel);

    // Busca alertas pela descrição com correspondência parcial (case-insensitive)
    @Query("SELECT a FROM Alerta a WHERE LOWER(a.descricaoAlerta) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    List<Alerta> searchByDescricao(@Param("descricao") String descricao);

    // Retorna alertas dentro de um intervalo de datas
    @Query("SELECT a FROM Alerta a WHERE a.dataAlerta BETWEEN :inicio AND :fim")
    List<Alerta> findByDataAlertaBetween(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
