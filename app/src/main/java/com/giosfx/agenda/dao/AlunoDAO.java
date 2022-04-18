package com.giosfx.agenda.dao;

import androidx.annotation.Nullable;

import com.giosfx.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizarIds();
    }

    private void atualizarIds() {
        contadorDeIds++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = buscarAlunoPorId(aluno);
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscarAlunoPorId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remover(Aluno aluno) {
        Aluno alunoDevolvido = buscarAlunoPorId(aluno);
        if(alunoDevolvido == null) return;
        alunos.remove(alunoDevolvido);
    }
}
