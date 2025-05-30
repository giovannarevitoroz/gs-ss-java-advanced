package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.ConfirmaPostagem;
import com.fiap.gs_swift_safety.model.ConfirmaPostagemId;
import com.fiap.gs_swift_safety.model.Postagem;
import com.fiap.gs_swift_safety.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfirmaPostagemRepository extends JpaRepository<ConfirmaPostagem, ConfirmaPostagemId> {

    // Buscar confirmações por usuário
    List<ConfirmaPostagem> findByUsuario(Usuario usuario);

    // Buscar confirmações por postagem
    List<ConfirmaPostagem> findByPostagem(Postagem postagem);

    // Verificar se um usuário confirmou uma postagem específica
    Optional<ConfirmaPostagem> findByUsuarioAndPostagem(Usuario usuario, Postagem postagem);
}
