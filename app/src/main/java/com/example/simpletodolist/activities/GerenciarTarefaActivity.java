import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodolist.R;
import com.example.simpletodolist.database.AppDatabase;
import com.example.simpletodolist.entities.Tarefa;

import java.util.Date;

public class GerenciarTarefaActivity extends AppCompatActivity {

    private EditText editTituloTarefa;
    private EditText editDescricaoTarefa;
    private CalendarView calendarioTarefa;
    private EditText editHoraTarefa;
    private EditText editTipoTarefa;
    private Spinner spinnerStatusTarefa;
    private EditText editLocalizacaoTarefa;
    private Button buttonAdicionarTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciartarefa);

        editTituloTarefa = findViewById(R.id.editTituloTarefa);
        editDescricaoTarefa = findViewById(R.id.editDescricaoTarefa);
        calendarioTarefa = findViewById(R.id.calendarioTarefa);
        editHoraTarefa = findViewById(R.id.editHoraTarefa);
        editTipoTarefa = findViewById(R.id.editTipoTarefa);
        spinnerStatusTarefa = findViewById(R.id.spinnerStatusTarefa);
        editLocalizacaoTarefa = findViewById(R.id.editLocalizacaoTarefa);
        buttonAdicionarTarefa = findViewById(R.id.buttonAdicionarTarefa);

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

        Tarefa novaTarefa = new Tarefa(titulo, descricao, data, hora, "", tipo, status, localizacao);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        db.tarefaDao().inserirTarefa(novaTarefa);
    }
}