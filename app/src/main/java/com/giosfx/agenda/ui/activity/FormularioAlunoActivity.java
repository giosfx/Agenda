package com.giosfx.agenda.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.giosfx.agenda.R;
import com.giosfx.agenda.dao.AlunoDAO;
import com.giosfx.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Novo aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APP_BAR);

        iniciarCampos();

        configurarBotaoSalvar();
    }

    private void configurarBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.acticity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> {
            Aluno alunoCriado = criarAluno();
            salvarAluno(alunoCriado);
        });
    }

    private void iniciarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAluno(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    @NonNull
    private Aluno criarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}