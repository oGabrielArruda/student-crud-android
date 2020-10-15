package br.unicamp.cotuca.studentschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.cotuca.studentschedule.Adapters.AlunoAdapter;
import br.unicamp.cotuca.studentschedule.Models.Aluno;
import br.unicamp.cotuca.studentschedule.Retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetActivity extends AppCompatActivity {

    List<Aluno> alunoList;
    EditText edtRa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        edtRa = (EditText) findViewById(R.id.edtRa);
        alunoList = new ArrayList<Aluno>();
        final AlunoAdapter alunoAdapter = new AlunoAdapter(this, R.layout.aluno_item, alunoList);
        final ListView alunos = (ListView) findViewById(R.id.listViewAlunos);
        alunos.setAdapter(alunoAdapter);

        RetrofitConfig retrofit = new RetrofitConfig();
        Call<Aluno[]> call = retrofit.getAlunosService().buscarAlunos();

        call.enqueue(new Callback<Aluno[]>() {
            @Override
            public void onResponse(Call<Aluno[]> call, Response<Aluno[]> response) {
                if(response.isSuccessful()) {
                    Aluno[] alunosSelecionados = response.body();
                    for(int i = 0; i < alunosSelecionados.length; i++)
                        alunoAdapter.add(alunosSelecionados[i]);
                } else {
                    Toast.makeText(GetActivity.this, "Falha no request", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Aluno[]> call, Throwable t) {
                Toast.makeText(GetActivity.this, "Falha no request", Toast.LENGTH_LONG);
            }
        });


        edtRa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 5) {
                    RetrofitConfig retrofit = new RetrofitConfig();
                    Call<Aluno> call = retrofit.getAlunosService().buscarAluno(Integer.parseInt(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                
            }
        });
    }
}