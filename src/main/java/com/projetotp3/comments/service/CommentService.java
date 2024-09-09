package com.projetotp3.comments.service;

import com.projetotp3.comments.client.ProjectClient;
import com.projetotp3.comments.client.ProjectDTO;
import com.projetotp3.comments.model.Comentario;
import com.projetotp3.comments.model.Secao;
import com.projetotp3.comments.repo.ComentarioRepository;
import com.projetotp3.comments.repo.SecaoRepository;
import org.hibernate.cache.internal.TimestampsCacheEnabledImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    SecaoRepository secaoRepository;
    @Autowired
    ProjectClient projectClient;

    //secao

    public Optional<Secao> getSecao(Long id) {
        return secaoRepository.findById(id);
    }

    public List<Secao> getAll() { return secaoRepository.findAll();}

    public Optional<Secao> getSecaoPorProjetoID(Long project_id) {
        for (Secao secao : secaoRepository.findAll()) {
            if (secao.getId_projeto() == project_id)
                return Optional.of(secao);
        }
        return Optional.empty();
    }

    public Optional<Secao> saveSecao(Secao secao) {
        if ( getSecaoPorProjetoID(secao.getId_projeto()).isPresent() || projectClient.getProjectById(secao.getId_projeto()).isPresent() ) {
            return Optional.of(secaoRepository.save(secao));
        }
        return Optional.empty();
    }

    public boolean deletarSecao(Long id) {
        Optional<Secao> secao = getSecao(id);
        if (secao.isPresent()) {
            secaoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Comentario> getComentario(Long id) {
        return comentarioRepository.findById(id);
    }

    public Optional<Comentario> saveComentario(Comentario comentario) {
        if ( getSecao( comentario.getSecao().getId() ).isPresent() )
            return Optional.of(comentarioRepository.save(comentario));
        return Optional.empty();
    }

    public boolean deletarComentario(Long id) {
        Optional<Comentario> comentario = getComentario(id);
        if (comentario.isPresent()) {
            comentarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean editarComentario(Long id, String conteudo) {
        Optional<Comentario> comentario = getComentario(id);
        if (comentario.isPresent()) {
            if (conteudo.length() > 1) {
                Comentario comentario_up = comentario.get();
                comentario_up.setConteudo(conteudo);
                saveComentario(comentario_up);
            } else
                deletarComentario(id);
            return true;
        }
        return false;
    }

    // funcoes

    public boolean postarComentario(Long secao_id, Comentario comentario) {
        Optional<Secao> secao = getSecao(secao_id);
        if (secao.isPresent()) {
            comentario.setSecao(secao.get());
            comentario.setDate(new Timestamp(System.currentTimeMillis()));
            saveComentario(comentario);
            return true;
        }
        return false;
    }

}
