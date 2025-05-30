package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Native Query para buscar por e-mail
    @Query(value = "SELECT * FROM Usuario WHERE email_usuario = :email", nativeQuery = true)
    Optional<Usuario> findByEmailUsuario(@Param("email") String emailUsuario);

    // JPQL (PL/SQL) para buscar por nome com paginação e filtro
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nomeUsuario) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Usuario> findByNomeUsuario(@Param("nome") String nomeUsuario, Pageable pageable);

    // Ordenação por nome ASC
    List<Usuario> findAllByOrderByNomeUsuarioAsc();

    // Ordenação por nome DESC
    List<Usuario> findAllByOrderByNomeUsuarioDesc();

    // Paginação padrão
    Page<Usuario> findAll(Pageable pageable);
}
