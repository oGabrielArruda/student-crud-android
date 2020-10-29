package br.unicamp.cotuca.studentschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.unicamp.cotuca.studentschedule.Models.Aluno;
import br.unicamp.cotuca.studentschedule.Retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button btnIncluir = (Button) findViewById(R.id.btnInserirAluno);
        final EditText edtRa = (EditText) findViewById(R.id.edtRaPost);
        final EditText edtNome = (EditText) findViewById(R.id.edtNomePost);
        final EditText edtEmail = (EditText) findViewById(R.id.edtEmailPost);

        final TextView tvRa = (TextView) findViewById(R.id.raInserido);
        final TextView tvNome = (TextView) findViewById(R.id.nomeInserido);
        final TextView tvEmail = (TextView) findViewById(R.id.emailInserido);

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ra = edtRa.getText().toString();
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                Toast.makeText(PostActivity.this, email, Toast.LENGTH_SHORT);
                if(ra == null || ra.isEmpty() || nome == null || nome.isEmpty() || email == null || email.isEmpty())
                    Toast.makeText(PostActivity.this, "Ra nulo", Toast.LENGTH_SHORT);
                else
                {
                    final Aluno aluno = new Aluno(Integer.parseInt(ra), nome, email);
                    Call<Aluno> call = new RetrofitConfig().getAlunosService().cadastrarAluno(aluno);
                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            if(response.isSuccessful()) {
                                Aluno alunoInserido = response.body();
                                tvRa.setText("RA: " + aluno.getRa());
                                tvNome.setText("Nome: " + aluno.getNome());
                                tvEmail.setText("Email: " + aluno.getEmail());
                            }
                            else
                                Toast.makeText(PostActivity.this, "Erro ao inserir aluno", Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                            Toast.makeText(PostActivity.this, "Erro ao inserir aluno", Toast.LENGTH_SHORT);
                        }
                    });
                }




            }
        });
    }
}