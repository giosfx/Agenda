package com.giosfx.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.giosfx.agenda.dao.AlunoDAO;
import com.giosfx.agenda.model.Aluno;
import com.giosfx.agenda.ui.adapter.ListaAlunoAdapter;

public class ListaAlunosView {

    private final AlunoDAO dao;
    private final ListaAlunoAdapter adapter;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunoAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Remover aluno")
                .setMessage("Deseja realmente remover esse aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remover(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void remover(Aluno aluno) {
        dao.remover(aluno);
        adapter.remover(aluno);
        atualizarAlunos();
    }

    public void atualizarAlunos() {
        adapter.atualizar(dao.todos());
    }

    public void configurarAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
