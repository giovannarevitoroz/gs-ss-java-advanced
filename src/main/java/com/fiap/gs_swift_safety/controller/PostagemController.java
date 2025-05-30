package com.fiap.gs_swift_safety.controller;

import com.fiap.gs_swift_safety.dto.PostagemDTO;
import com.fiap.gs_swift_safety.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public List<PostagemDTO> findAll() {
        return postagemService.findAll();
    }

    @GetMapping("/{id}")
    public PostagemDTO findById(@PathVariable Long id) {
        return postagemService.findById(id);
    }

    @PostMapping
    public PostagemDTO create(@RequestBody PostagemDTO dto) {
        return postagemService.save(dto);
    }

    @PutMapping("/{id}")
    public PostagemDTO update(@PathVariable Long id, @RequestBody PostagemDTO dto) {
        return postagemService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public List<PostagemDTO> findByTitulo(@PathVariable String titulo) {
        return postagemService.findByTitulo(titulo);
    }

    @GetMapping("/datas")
    public List<PostagemDTO> findByDataRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fim) {
        return postagemService.findByDataRange(inicio, fim);
    }

    @GetMapping("/tipo/{tipo}")
    public List<PostagemDTO> findByTipo(@PathVariable String tipo) {
        return postagemService.findByTipo(tipo);
    }

    @GetMapping("/status/{status}")
    public List<PostagemDTO> findByStatus(@PathVariable String status) {
        return postagemService.findByStatus(status);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<PostagemDTO> findByUsuario(@PathVariable Long usuarioId) {
        return postagemService.findByUsuario(usuarioId);
    }

    @GetMapping("/endereco/{enderecoId}")
    public List<PostagemDTO> findByEndereco(@PathVariable Long enderecoId) {
        return postagemService.findByEndereco(enderecoId);
    }

    @GetMapping("/tipodesastre/{tipoDesastreId}")
    public List<PostagemDTO> findByTipoDesastre(@PathVariable Long tipoDesastreId) {
        return postagemService.findByTipoDesastre(tipoDesastreId);
    }

    @GetMapping("/ordenado/titulo")
    public List<PostagemDTO> findAllOrderByTituloAsc() {
        return postagemService.findAllOrderByTituloAsc();
    }

    @GetMapping("/ordenado/data/desc")
    public List<PostagemDTO> findAllOrderByDataDesc() {
        return postagemService.findAllOrderByDataDesc();
    }

    @GetMapping("/ordenado/data/asc")
    public List<PostagemDTO> findAllOrderByDataAsc() {
        return postagemService.findAllOrderByDataAsc();
    }
}
