package com.example.simpletodolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.simpletodolist.dao.TarefaDao;
import com.example.simpletodolist.dao.UsuarioDao;
import com.example.simpletodolist.entities.Tarefa;
import com.example.simpletodolist.entities.Usuario;

@Database(entities = {Usuario.class, Tarefa.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
    public abstract TarefaDao tarefaDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "SimpleToDoDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}