package com.example.simpletodolist.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText senhaEditText;
    private AppDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailEditText = findViewById(R.id.email);
        senhaEditText = findViewById(R.id.senha);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        // Inicializando o banco de dados
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database")
                .allowMainThreadQueries()
                .build();


        btnCadastrar.setOnClickListener(View -> {
            Intent intent = new Intent(LoginActivity.this, CadastraUsuarioActivity.class);
            startActivity(intent);
            finish();
        });

        btnLogin.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String senha = senhaEditText.getText().toString().trim();

            // Validação de email e senha
            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("Email nulo");
                return;
            }

            if (TextUtils.isEmpty(senha)) {
                senhaEditText.setError("Senha vazia");
                return;
            }

            //Autenticação - utiliza o query recomendado
            Usuario usuario = db.usuarioDao().getUsuarioByEmailAndSenha(email, senha);

            if (usuario != null) {
                Toast.makeText(LoginActivity.this, "Login concluído", Toast.LENGTH_SHORT).show();
                //Navega para lista de cidades
                Intent intent = new Intent(LoginActivity.this, TelaPrincipal.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}