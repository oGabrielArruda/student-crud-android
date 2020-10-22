package br.unicamp.cotuca.studentschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.unicamp.cotuca.studentschedule.Models.Aluno;
import br.unicamp.cotuca.studentschedule.Retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        EditText edtRa = findViewById(R.id.edtRaDelete);
        Button btnDelete = findViewById(R.id.btnExcluirAluno);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ra = edtRa.getText().toString();
                if (ra == null)
                    Toast.makeText(DeleteActivity.this, "Ra nulo", Toast.LENGTH_SHORT);
                else {
                    RetrofitConfig retrofit = new RetrofitConfig();
                    Call<Aluno> call = retrofit.getAlunosService().removerAluno(Integer.parseInt(ra));
                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(DeleteActivity.this, "Aluno deletado", Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}