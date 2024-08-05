package com.projetotp3.comments.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@FeignClient("ProjetoTp2")
public interface ProjectClient {
    @GetMapping("/project/{id}")
    Optional<ProjectDTO> getProjectById(@PathVariable Long id);

    @GetMapping("/project")
    List<ProjectDTO> getProjects();
}
