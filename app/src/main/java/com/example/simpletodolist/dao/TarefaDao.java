package com.example.simpletodolist.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.simpletodolist.entities.Tarefa;
import java.util.List;

@Dao
public interface TarefaDao {

    @Insert
    void inserirTarefa(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa")
    List<Tarefa> listarTarefas();

    @Query("SELECT * FROM Tarefa WHERE id = :idTarefa")
    Tarefa buscarTarefaPorId(int idTarefa);

    @Delete
    void deletarTarefa(Tarefa tarefa);

    @Update
    void atualizarTarefa(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa WHERE tipo = :tipo")
    List<Tarefa> listarTarefasPorTipo(String tipo);

    // Outros métodos de consulta ou atualização podem ser adicionados conforme necessário
}