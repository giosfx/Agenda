package com.giosfx.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.giosfx.agenda.R;
import com.giosfx.agenda.dao.AlunoDAO;
import com.giosfx.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String CHAVE_ALUNO = "aluno";
    private static final String TITULO_APP_BAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APP_BAR_EDITAR_ALUNO = "Editar aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        iniciarCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent it = getIntent();

        if (it.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APP_BAR_EDITAR_ALUNO);
            aluno = it.getExtras().getParcelable(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APP_BAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizarFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.editar(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void iniciarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}