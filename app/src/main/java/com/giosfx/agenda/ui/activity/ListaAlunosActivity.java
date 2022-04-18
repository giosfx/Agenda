package com.giosfx.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giosfx.agenda.R;
import com.giosfx.agenda.dao.AlunoDAO;
import com.giosfx.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de Alunos";
    public static final String CHAVE_ALUNO = "aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);
        configurarFabNovoAluno();
        configurarLista();

        for (int i = 0; i < 5; i++) {
            dao.salva(new Aluno("Teste" + i, "67981646419", "giosfx@gmail.com"));
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.activity_lista_alunos_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remover(alunoEscolhido);
        }

        return super.onContextItemSelected(item);
    }

    private void remover(Aluno aluno) {
        dao.remover(aluno);
        atualizarAlunos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarAlunos();
    }

    private void atualizarAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configurarLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configurarAdapter(listaDeAlunos);
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

    private void configurarAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        listaDeAlunos.setAdapter(adapter);
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