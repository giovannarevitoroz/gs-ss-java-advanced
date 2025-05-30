package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.TipoDesastre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDesastreRepository extends JpaRepository<TipoDesastre, Long> {

    //  JPQL (PLSQL) - Buscar por nome do desastre (ignora maiúsculas/minúsculas)
    @Query("SELECT t FROM TipoDesastre t WHERE LOWER(t.nomeDesastre) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<TipoDesastre> findByNomeDesastre(@Param("nome") String nomeDesastre);

    //  Native Query - Buscar por descrição exata
    @Query(value = "SELECT * FROM TIPO_DESASTRE WHERE descricao_desastre = :descricao", nativeQuery = true)
    List<TipoDesastre> findByDescricaoDesastre(@Param("descricao") String descricaoDesastre);

    // Ordenar por nome ASC
    List<TipoDesastre> findAllByOrderByNomeDesastreAsc();

    //  Ordenar por nome DESC
    List<TipoDesastre> findAllByOrderByNomeDesastreDesc();

    //  Paginação de todos os desastres
    Page<TipoDesastre> findAll(Pageable pageable);
}
