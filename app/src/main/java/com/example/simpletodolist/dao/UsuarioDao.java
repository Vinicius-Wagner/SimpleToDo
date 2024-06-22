package com.example.simpletodolist.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.simpletodolist.entities.Usuario;
import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert
    void inserirUsuario(Usuario usuario);

    @Query("SELECT * FROM Usuario")
    List<Usuario> listarUsuarios();

    @Query("SELECT * FROM Usuario WHERE id = :idUsuario")
    Usuario buscarUsuarioPorId(int idUsuario);

    @Query("SELECT * FROM Usuario WHERE email = :emailUsuario")
    Usuario buscarUsuarioPorEmail(String emailUsuario);

    @Query("DELETE FROM Usuario WHERE id = :idUsuario")
    void deletarUsuario(int idUsuario);

    // Outros métodos de consulta ou atualização podem ser adicionados conforme necessário
}