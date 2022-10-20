package com.project.api.todolist.controllers;

import com.project.api.todolist.dto.TarefaDTO;
import com.project.api.todolist.entities.Tarefa;
import com.project.api.todolist.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/todolist")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listar(){
        List<TarefaDTO> list = tarefaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Integer id){
        TarefaDTO tarefaDTO = tarefaService.findById(id);
        return ResponseEntity.ok().body(tarefaDTO);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> criar(@RequestBody TarefaDTO dto){
        Optional<Tarefa> existe = tarefaService.existeTask(dto.getDescricao());
        if(existe.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        dto = tarefaService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizar(@RequestBody TarefaDTO dto) {
        TarefaDTO aux = tarefaService.findById(dto.getId());
        if (aux.getId() == null){
            return ResponseEntity.notFound().build();
        }
        TarefaDTO tarefa = tarefaService.uptade(dto);
        return ResponseEntity.ok().body(tarefa);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TarefaDTO> setTarefaStatus(@PathVariable Integer id){
        TarefaDTO tarefaDTO = tarefaService.setConcluidoOuNaoConcluido(id);
        return ResponseEntity.ok().body(tarefaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        TarefaDTO aux = tarefaService.findById(id);
        if(aux.getId() == null){
            return ResponseEntity.notFound().build();
        }
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
