package com.example.simpletodolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodolist.R;

public class TelaPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal);

        Button buttonGerenciarTarefa = findViewById(R.id.buttonGerenciarTarefa);
        buttonGerenciarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrincipal.this, GerenciarTarefaActivity.class);
                startActivity(intent);
            }
        });
    }
}
