package com.projetotp3.comments.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetotp3.comments.client.ProjectDTO;
import com.projetotp3.comments.model.EnumProceed;
import com.projetotp3.comments.model.Secao;
import com.projetotp3.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class ProjectConsumer {
    private final ObjectMapper objectMapper;
    private final CommentService commentService;

    @RabbitListener(queues = {"newproj-queue"})
    public void receive(@Payload String json) {
        try{
            log.info(json);
            ProjectDTO project = objectMapper.readValue(json, ProjectDTO.class);
            Secao sec = new Secao();
            sec.setId_projeto(project.id());
            log.info("comentarios criados: ", commentService.saveSecao(sec).get().getId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e);

        }

    }
}