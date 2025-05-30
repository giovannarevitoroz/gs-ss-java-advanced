package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>, JpaSpecificationExecutor<Sensor> {

    // Busca todos com paginação e ordenação
    Page<Sensor> findAll(Pageable pageable);

    // Filtro por nome ignorando maiúsculas/minúsculas
    List<Sensor> findByNomeSensorIgnoreCase(String nomeSensor);

    // Filtro por tipo do sensor
    List<Sensor> findByTipoSensor(String tipoSensor);

    // Filtro por ID
    Optional<Sensor> findById(Long idSensor);

    // JPQL: Busca por nome contendo parte do texto e tipo
    @Query("SELECT s FROM Sensor s WHERE LOWER(s.nomeSensor) LIKE LOWER(CONCAT('%', :nome, '%')) AND s.tipoSensor = :tipo")
    List<Sensor> buscarPorNomeEPorTipoJPQL(@Param("nome") String nome, @Param("tipo") String tipo);

    // Native Query: Busca sensores por nome e tipo usando SQL nativo
    @Query(value = "SELECT * FROM SENSOR WHERE LOWER(nome_sensor) LIKE LOWER(CONCAT('%', :nome, '%')) AND tipo_sensor = :tipo", nativeQuery = true)
    List<Sensor> buscarPorNomeEPorTipoNative(@Param("nome") String nome, @Param("tipo") String tipo);
}
