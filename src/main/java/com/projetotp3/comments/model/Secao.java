package com.projetotp3.comments.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TB_SECAO")
@Entity
@Data
@NoArgsConstructor
public class Secao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long id_projeto;
    @OneToMany(mappedBy = "secao", cascade = CascadeType.ALL)
    List<Comentario> comentarios;
}
