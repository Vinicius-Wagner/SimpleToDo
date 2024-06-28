package com.example.simpletodolist.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.simpletodolist.R;
import com.example.simpletodolist.database.AppDatabase;
import com.example.simpletodolist.entities.Usuario;

public class CadastraUsuarioActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private Button btnCadastrar;
    private AppDatabase db;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastra_usuario);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app_database")
                .allowMainThreadQueries() // Permite consultas no thread principal (não recomendado para produção)
                .fallbackToDestructiveMigration()
                .build();

        nomeEditText = findViewById(R.id.editTextNome);
        emailEditText = findViewById(R.id.editTextEmail);
        senhaEditText = findViewById(R.id.editTextSenha);
        btnCadastrar = findViewById(R.id.buttonCadastrar);

        btnCadastrar.setOnClickListener(v -> cadastrarUsuario());
    }

    private void cadastrarUsuario() {

        String nome = nomeEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String senha = senhaEditText.getText().toString().trim();

        // Valida os campos
        if (TextUtils.isEmpty(nome)) {
            nomeEditText.setError("Nome não pode estar vazio");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email não pode estar vazio");
            return;
        }
        if (TextUtils.isEmpty(senha)) {
            senhaEditText.setError("Senha não pode estar vazia");
            return;
        }


        //Cria um novo usuário e o salva no banco de dados
        Usuario usuario = new Usuario(nome, email, senha);
        db.usuarioDao().inserirUsuario(usuario);


        Toast.makeText(this, "Usuário cadastrado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CadastraUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}