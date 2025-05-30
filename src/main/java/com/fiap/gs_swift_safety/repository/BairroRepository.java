package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    // Buscar todos os bairros
    List<Bairro> findAll();

    // Buscar bairro por ID
    Optional<Bairro> findById(Long id);

    // Buscar bairro por nome exato
    Optional<Bairro> findByNomeBairro(String nomeBairro);

    // Buscar bairros que contenham parte do nome (útil para filtros e pesquisas)
    List<Bairro> findByNomeBairroContainingIgnoreCase(String nomeBairro);

    // Buscar bairros por zona
    List<Bairro> findByZonaBairro(String zonaBairro);
}
