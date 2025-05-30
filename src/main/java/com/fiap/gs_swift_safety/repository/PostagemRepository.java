package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Postagem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    // Find all e find by id já existem pelo JpaRepository

    // Buscar por título (JPQL)
    @Query("SELECT p FROM Postagem p WHERE LOWER(p.tituloPostagem) = LOWER(:titulo)")
    List<Postagem> findByTituloPostagemIgnoreCase(@Param("titulo") String titulo);

    // Buscar entre datas (JPQL)
    @Query("SELECT p FROM Postagem p WHERE p.dataPostagem BETWEEN :inicio AND :fim")
    List<Postagem> findPostagensPorDatas(@Param("inicio") Date inicio, @Param("fim") Date fim);

    // Buscar por tipo (JPQL)
    @Query("SELECT p FROM Postagem p WHERE LOWER(p.tipoPostagem) = LOWER(:tipo)")
    List<Postagem> findPostagensPorTipo(@Param("tipo") String tipo);

    // Buscar por status (JPQL)
    @Query("SELECT p FROM Postagem p WHERE LOWER(p.statusPostagem) = LOWER(:status)")
    List<Postagem> findPostagensPorStatus(@Param("status") String status);

    // Buscar por usuário (JPQL)
    @Query("SELECT p FROM Postagem p WHERE p.usuario.id = :usuarioId")
    List<Postagem> findPostagensPorUsuario(@Param("usuarioId") Long usuarioId);

    // Buscar por endereço (JPQL)
    @Query("SELECT p FROM Postagem p WHERE p.endereco.id = :enderecoId")
    List<Postagem> findPostagensPorEndereco(@Param("enderecoId") Long enderecoId);

    // Buscar por tipo de desastre (JPQL)
    @Query("SELECT p FROM Postagem p WHERE p.tipoDesastre.id = :tipoDesastreId")
    List<Postagem> findPostagensPorTipoDesastre(@Param("tipoDesastreId") Long tipoDesastreId);

    // Buscar todas ordenadas por título asc
    List<Postagem> findAllByOrderByTituloPostagemAsc();

    // Buscar todas ordenadas por data (mais nova)
    List<Postagem> findAllByOrderByDataPostagemDesc();

    // Buscar todas ordenadas por data (mais antiga)
    List<Postagem> findAllByOrderByDataPostagemAsc();

    // Buscar entre datas (Native)
    @Query(value = "SELECT * FROM POSTAGEM WHERE data_postagem BETWEEN :inicio AND :fim", nativeQuery = true)
    List<Postagem> findPostagensPorDatasNative(@Param("inicio") Date inicio, @Param("fim") Date fim);

}
