package com.example.simpletodolist.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodolist.R;
import com.example.simpletodolist.database.AppDatabase;
import com.example.simpletodolist.entities.Tarefa;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GerenciarTarefaActivity extends AppCompatActivity {

    private EditText editTituloTarefa;
    private EditText editDescricaoTarefa;
    private CalendarView calendarioTarefa;
    private EditText editHoraTarefa;
    private EditText editTipoTarefa;
    private Spinner spinnerStatusTarefa;
    private EditText editLocalizacaoTarefa;
    private Button buttonAdicionarTarefa;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciartarefa);

        editTituloTarefa = findViewById(R.id.editTituloTarefaDetalhe);
        editDescricaoTarefa = findViewById(R.id.editDescricaoTarefaDetalhe);
        calendarioTarefa = findViewById(R.id.calendarioTarefaDetalhe);
        editHoraTarefa = findViewById(R.id.editHoraTarefaDetalhe);
        editTipoTarefa = findViewById(R.id.editTipoTarefaDetalhe);
        editLocalizacaoTarefa = findViewById(R.id.editLocalizacaoTarefaDetalhe);
        buttonAdicionarTarefa = findViewById(R.id.buttonAdicionarTarefa);

        spinnerStatusTarefa = findViewById(R.id.spinnerStatusTarefaDetalhe);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusTarefa.setAdapter(adapter);

        spinnerStatusTarefa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Pega o item selecionado
                String status = parentView.getItemAtPosition(position).toString();
                Toast.makeText(parentView.getContext(), "Selected: " + status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Não faz nada
            }
        });

        buttonAdicionarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarTarefa();
            }
        });
    }

    private void adicionarTarefa() {
        String titulo = editTituloTarefa.getText().toString();
        String descricao = editDescricaoTarefa.getText().toString();
        Date data = new Date(calendarioTarefa.getDate());
        String hora = editHoraTarefa.getText().toString();
        String tipo = editTipoTarefa.getText().toString();
        String status = spinnerStatusTarefa.getSelectedItem().toString();
        String localizacao = editLocalizacaoTarefa.getText().toString();

        final Tarefa novaTarefa = new Tarefa(titulo, descricao, data, hora, "", tipo, status, localizacao);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                db.tarefaDao().inserirTarefa(novaTarefa);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Sucesso ao inserir nova tarefa", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
