package com.example.simpletodolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodolist.R;
import com.example.simpletodolist.database.AppDatabase;
import com.example.simpletodolist.entities.Tarefa;

import java.util.List;

public class TelaPrincipal extends AppCompatActivity {
    private ListView listViewTarefas;
    private List<Tarefa> tarefaList;
    private AppDatabase db;
    private EditText editTipoTarefaBuscar;
    private Button buttonBuscarPorTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal);

        db = AppDatabase.getDatabase(this);

        editTipoTarefaBuscar = findViewById(R.id.editTipoTarefaBuscar);
        buttonBuscarPorTipo = findViewById(R.id.buttonBuscarPorTipo);

        listViewTarefas = findViewById(R.id.listViewTarefas);
        listViewTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = tarefaList.get(position);
                Intent intent = new Intent(TelaPrincipal.this, DetalheTarefaActivity.class);
                intent.putExtra("TAREFA", tarefa);
                startActivity(intent);
            }
        });

        Button buttonGerenciarTarefa = findViewById(R.id.buttonGerenciarTarefa);
        buttonGerenciarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrincipal.this, GerenciarTarefaActivity.class);
                startActivity(intent);
            }
        });

        buttonBuscarPorTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoTarefa = editTipoTarefaBuscar.getText().toString().trim();
                carregarTarefasPorTipo(tipoTarefa);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarTarefas();
    }

    void carregarTarefas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tarefaList = db.tarefaDao().listarTarefas();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(TelaPrincipal.this, android.R.layout.simple_list_item_1, tarefaList);
                        listViewTarefas.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    void carregarTarefasPorTipo(final String tipo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tarefaList = db.tarefaDao().listarTarefasPorTipo(tipo);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(TelaPrincipal.this, android.R.layout.simple_list_item_1, tarefaList);
                        listViewTarefas.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}