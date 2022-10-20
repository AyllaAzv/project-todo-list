package com.project.api.todolist.repositories;

import com.project.api.todolist.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query(nativeQuery = true, value = "select * from tarefa as tb where LOWER(tb.descricao) LIKE LOWER(:descricao)")
    Optional<Tarefa> existsTarefaWithDescription(@Param("descricao") String descricao);
}
