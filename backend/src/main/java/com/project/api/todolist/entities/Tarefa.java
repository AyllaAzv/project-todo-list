package com.project.api.todolist.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarefa")
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_id_seq")
    @SequenceGenerator(name = "tarefa_id_seq", sequenceName = "tarefa_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "concluido")
    private TarefaStatus flag;
}
