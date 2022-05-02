package com.giosfx.agenda;

import android.app.Application;

import com.giosfx.agenda.dao.AlunoDAO;
import com.giosfx.agenda.model.Aluno;

public class AgendaApplication extends Application {

    final AlunoDAO dao = new AlunoDAO();

    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 0; i < 5; i++) {
            dao.salva(new Aluno("Teste" + i, "67981646419", "giosfx@gmail.com"));
        }
    }
}
