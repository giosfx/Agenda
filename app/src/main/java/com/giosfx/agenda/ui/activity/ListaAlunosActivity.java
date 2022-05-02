package com.giosfx.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giosfx.agenda.R;
import com.giosfx.agenda.model.Aluno;
import com.giosfx.agenda.ui.ListaAlunosView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de Alunos";
    public static final String CHAVE_ALUNO = "aluno";

    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);

        configurarFabNovoAluno();
        configurarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizarAlunos();
    }

    private void configurarLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosView.configurarAdapter(listaDeAlunos);
        configurarListenerPorItemListadoClick(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configurarListenerPorItemListadoClick(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, i, l) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(i);
            abreFormularioEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.acticity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> abrirFormularioModoInsereAluno());
    }

    private void abrirFormularioModoInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class));
    }
}