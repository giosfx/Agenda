package com.giosfx.agenda.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.giosfx.agenda.R;

public class ViewHolder {
    final TextView nome;
    final TextView telefone;

    public ViewHolder(View view) {
        nome = view.findViewById(R.id.activity_item_aluno_nome);
        telefone = view.findViewById(R.id.activity_item_aluno_telefone);
    }
}
