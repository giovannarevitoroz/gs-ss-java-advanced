package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findAll();

    Optional<Endereco> findById(Long id);

    @Query("SELECT e FROM Endereco e WHERE LOWER(e.bairro.nomeBairro) = LOWER(:nomeBairro)")
    List<Endereco> findByNomeExatoDoBairro(@Param("nomeBairro") String nomeBairro);

    @Query("SELECT e FROM Endereco e WHERE LOWER(e.bairro.nomeBairro) LIKE LOWER(CONCAT('%', :nomeBairro, '%'))")
    List<Endereco> findByBairroNomeContainingIgnoreCase(@Param("nomeBairro") String nomeBairro);

    List<Endereco> findByCep(String cep);

    List<Endereco> findByLogradouroContainingIgnoreCase(String logradouro);

    @Query("SELECT e FROM Endereco e WHERE e.bairro.id = :bairroId")
    List<Endereco> findByBairroId(@Param("bairroId") Long bairroId);
}
