package com.project.api.todolist.service;

import com.project.api.todolist.dto.TarefaDTO;
import com.project.api.todolist.entities.Tarefa;
import com.project.api.todolist.entities.TarefaStatus;
import com.project.api.todolist.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public List<TarefaDTO> findAll(){
        List<Tarefa> list = tarefaRepository.findAll();
        return list.stream().map(x -> new TarefaDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TarefaDTO findById(Integer id){
        Optional obj = tarefaRepository.findById(id);
        if(obj.isPresent()){
            Tarefa tarefa = (Tarefa) obj.get();
            return new TarefaDTO(tarefa);
        }
        return new TarefaDTO();
    }

    @Transactional
    public TarefaDTO insert(TarefaDTO dto){
        Tarefa tarefa = new Tarefa(null, dto.getDescricao(), TarefaStatus.NAOCONCLUIDA);
        tarefa = tarefaRepository.save(tarefa);
        return new TarefaDTO(tarefa);
    }

    public Optional<Tarefa> existeTask(String descricao){
        return tarefaRepository.existsTarefaWithDescription(descricao);
    }

    @Transactional
    public TarefaDTO uptade(TarefaDTO dto){
        Tarefa obj = tarefaRepository.findById(dto.getId()).get();
        obj.setDescricao(dto.getDescricao());
        obj = tarefaRepository.save(obj);
        return new TarefaDTO(obj);
    }

    @Transactional
    public TarefaDTO setConcluidoOuNaoConcluido(Integer id){
        Tarefa tarefa = tarefaRepository.findById(id).get();
        if(tarefa.getFlag().equals(TarefaStatus.NAOCONCLUIDA)){
            tarefa.setFlag(TarefaStatus.CONCLUIDA);
        } else {
            tarefa.setFlag(TarefaStatus.NAOCONCLUIDA);
        }
        tarefa = tarefaRepository.save(tarefa);
        return new TarefaDTO(tarefa);
    }

    @Transactional
    public String delete(Integer id){
        tarefaRepository.deleteById(id);
        return "Tarefa excluida com sucesso!";
    }

}
