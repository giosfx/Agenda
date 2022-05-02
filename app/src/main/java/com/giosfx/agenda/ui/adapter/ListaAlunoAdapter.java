package com.giosfx.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.giosfx.agenda.R;
import com.giosfx.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunoAdapter extends BaseAdapter {

    private final Context context;
    private final List<Aluno> alunos = new ArrayList<>();

    public ListaAlunoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater
                    .from(context)
                    .inflate(R.layout.activity_item_aluno,
                            parent,
                            false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        Aluno alunoDevolvido = alunos.get(posicao);

        holder.nome.setText(alunoDevolvido.getNome());
        holder.telefone.setText(alunoDevolvido.getTelefone());
        return view;
    }

    public void atualizar(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remover(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }
}

