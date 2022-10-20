package com.project.api.todolist.dto;

import com.project.api.todolist.entities.Tarefa;
import com.project.api.todolist.entities.TarefaStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    private Integer id;

    private String descricao;

    private TarefaStatus flag;

    public TarefaDTO(Tarefa tarefa){
        id = tarefa.getId();
        descricao = tarefa.getDescricao();
        flag = tarefa.getFlag();
    }
}
