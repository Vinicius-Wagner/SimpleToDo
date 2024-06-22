package com.example.simpletodolist.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
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

    @Query("DELETE FROM Tarefa WHERE id = :idTarefa")
    void deletarTarefa(int idTarefa);


    // Outros métodos de consulta ou atualização podem ser adicionados conforme necessário
}