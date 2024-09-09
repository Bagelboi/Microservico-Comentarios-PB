package com.projetotp3.comments.controller;

import com.projetotp3.comments.model.Comentario;
import com.projetotp3.comments.model.Secao;
import com.projetotp3.comments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/secoes/{secao_id}")
    public ResponseEntity<Secao> getSecao(@PathVariable Long secao_id) {
        Optional<Secao> secao = commentService.getSecao(secao_id);
        return secao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/secoes/")
    public List<Secao> getSecaos() {
        return commentService.getAll();
    }

    @GetMapping("/secoes/por_proj/{projeto_id}")
    public ResponseEntity<Secao> getSecaoPorProjeto(@PathVariable Long projeto_id) {
        Optional<Secao> secao = commentService.getSecaoPorProjetoID(projeto_id);
        return secao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/secoes")
    public ResponseEntity<Secao> criarSecao(@RequestBody Secao secao) {
        return commentService.saveSecao(secao).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/secoes/{secao_id}")
    public ResponseEntity<Void> deleteSecao(@PathVariable Long secao_id) {
        if (commentService.deletarSecao(secao_id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/secoes/{secao_id}/comentarios")
    public ResponseEntity<Void> postarComentario(@PathVariable Long secao_id, @RequestBody Comentario comentario) {
        if (commentService.postarComentario(secao_id, comentario)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/comentarios/{comentario_id}")
    public ResponseEntity<Void> editarComentario(@PathVariable Long comentario_id, @RequestBody String conteudo) {
        if (commentService.editarComentario(comentario_id, conteudo)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/comentarios/{comentario_id}")
    public ResponseEntity<Void> apagarComentario(@PathVariable Long comentario_id) {
        if (commentService.deletarComentario(comentario_id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}