package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nomeUsuario) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Usuario> findByNomeUsuario(@Param("nome") String nomeUsuario, Pageable pageable);
    List<Usuario> findAllByOrderByNomeUsuarioAsc();
    List<Usuario> findAllByOrderByNomeUsuarioDesc();
}
