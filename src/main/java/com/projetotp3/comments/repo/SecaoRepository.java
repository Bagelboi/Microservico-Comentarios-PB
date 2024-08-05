package com.projetotp3.comments.repo;

import com.projetotp3.comments.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long> {
}
