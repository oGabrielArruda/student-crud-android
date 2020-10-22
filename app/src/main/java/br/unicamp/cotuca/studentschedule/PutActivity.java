package br.unicamp.cotuca.studentschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.unicamp.cotuca.studentschedule.Models.Aluno;
import br.unicamp.cotuca.studentschedule.Retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        EditText edtRa = findViewById(R.id.edtRaPut);
        EditText edtNome = findViewById(R.id.edtNomePut);
        EditText edtEmail = findViewById(R.id.edtEmailPut);
        Button btnPut = findViewById(R.id.btnEditarAluno);
        TextView txtRa = findViewById(R.id.raEditado);
        TextView txtNome = findViewById(R.id.nomeEditado);
        TextView txtEmail = findViewById(R.id.emailEditado);

        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ra = edtRa.getText().toString();
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                if (ra != null && (nome !=null || email !=null)){
                    final Aluno aluno = new Aluno(Integer.parseInt(ra), nome, email);
                    Call<Aluno> call = new RetrofitConfig().getAlunosService().editarAluno(aluno);
                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            if(response.isSuccessful()) {
                                Aluno alunoInserido = response.body();
                                txtRa.setText("RA: " + aluno.getRa());
                                txtNome.setText("Nome: " + aluno.getNome());
                                txtEmail.setText("Email: " + aluno.getEmail());
                            }
                            else
                                Toast.makeText(PutActivity.this, "Erro ao editar aluno", Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                            Toast.makeText(PutActivity.this, "Erro ao editar aluno", Toast.LENGTH_SHORT);
                        }
                    });
                }
                else
                    Toast.makeText(PutActivity.this, "Ra nulo ou nenhuma alteração a fazer", Toast.LENGTH_SHORT);
            }
        });
    }
}