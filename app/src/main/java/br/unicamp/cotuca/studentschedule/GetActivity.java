package br.unicamp.cotuca.studentschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.cotuca.studentschedule.Adapters.AlunoAdapter;
import br.unicamp.cotuca.studentschedule.Models.Aluno;

public class GetActivity extends AppCompatActivity {

    List<Aluno> alunoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        alunoList = new ArrayList<Aluno>();
        AlunoAdapter alunoAdapter = new AlunoAdapter(this, R.layout.aluno_item, alunoList);
        ListView alunos = (ListView) findViewById(R.id.listViewAlunos);
        alunos.setAdapter(alunoAdapter);
    }
}