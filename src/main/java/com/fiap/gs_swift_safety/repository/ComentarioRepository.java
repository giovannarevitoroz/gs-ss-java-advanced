package com.fiap.gs_swift_safety.repository;

import com.fiap.gs_swift_safety.model.Comentario;
import com.fiap.gs_swift_safety.model.Postagem;
import com.fiap.gs_swift_safety.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Buscar todos os comentários
    List<Comentario> findAll();

    // Buscar comentário por ID
    Optional<Comentario> findById(Long idComentario);

    // Buscar comentários por postagem
    List<Comentario> findByPostagem(Postagem postagem);

    // Buscar comentários por usuário
    List<Comentario> findByUsuario(Usuario usuario);

    // Buscar comentários por postagem ordenados pela data
    List<Comentario> findByPostagemOrderByDataAsc(Postagem postagem);
}
