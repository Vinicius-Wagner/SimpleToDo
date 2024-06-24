package com.example.simpletodolist.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodolist.R;
import com.example.simpletodolist.database.AppDatabase;
import com.example.simpletodolist.entities.Tarefa;

public class DetalheTarefaActivity extends AppCompatActivity {

    private EditText editTituloTarefa, editDescricaoTarefa, editHoraTarefa, editTipoTarefa, editLocalizacaoTarefa;
    private Button buttonAtualizarTarefa, buttonExcluirTarefa, buttonAbrirLocalizacao;
    private Tarefa tarefa;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_tarefa);

        editTituloTarefa = findViewById(R.id.editTituloTarefaDetalhe);
        editDescricaoTarefa = findViewById(R.id.editDescricaoTarefaDetalhe);
        editHoraTarefa = findViewById(R.id.editHoraTarefaDetalhe);
        editTipoTarefa = findViewById(R.id.editTipoTarefaDetalhe);
        editLocalizacaoTarefa = findViewById(R.id.editLocalizacaoTarefaDetalhe);
        buttonAtualizarTarefa = findViewById(R.id.buttonSalvarTarefa);
        buttonExcluirTarefa = findViewById(R.id.buttonExcluirTarefa);
        buttonAbrirLocalizacao = findViewById(R.id.buttonAbrirLocalizacao);

        db = AppDatabase.getDatabase(getApplicationContext());

        tarefa = (Tarefa) getIntent().getSerializableExtra("TAREFA");
        if (tarefa != null) {
            editTituloTarefa.setText(tarefa.getTitulo());
            editDescricaoTarefa.setText(tarefa.getDescricao());
            editHoraTarefa.setText(tarefa.getHora());
            editTipoTarefa.setText(tarefa.getTipo());
            editLocalizacaoTarefa.setText(tarefa.getLocalizacao());
        } else {
            Toast.makeText(this, "Erro ao carregar tarefa", Toast.LENGTH_SHORT).show();
            finish();
        }

        buttonAtualizarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTarefa();
            }
        });

        buttonExcluirTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirTarefa();
            }
        });

        buttonAbrirLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLocalizacaoNoMaps();
            }
        });
    }

    private void salvarTarefa() {
        if (tarefa != null) {
            tarefa.setTitulo(editTituloTarefa.getText().toString());
            tarefa.setDescricao(editDescricaoTarefa.getText().toString());
            tarefa.setHora(editHoraTarefa.getText().toString());
            tarefa.setTipo(editTipoTarefa.getText().toString());
            tarefa.setLocalizacao(editLocalizacaoTarefa.getText().toString());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    db.tarefaDao().atualizarTarefa(tarefa);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DetalheTarefaActivity.this, "Tarefa atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        } else {
            Toast.makeText(this, "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();
        }
    }

    private void excluirTarefa() {
        if (tarefa != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    db.tarefaDao().deletarTarefa(tarefa);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DetalheTarefaActivity.this, "Tarefa excluída com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }).start();
        } else {
            Toast.makeText(this, "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirLocalizacaoNoMaps() {
        String localizacao = editLocalizacaoTarefa.getText().toString();
        if (!localizacao.isEmpty()) {
            String query = Uri.encode(localizacao);
            String geoUri = "geo:0,0?q=" + query;
            Uri uri = Uri.parse(geoUri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri)));
            }
        } else {
            Toast.makeText(this, "Localização não especificada", Toast.LENGTH_SHORT).show();
        }
    }
}
