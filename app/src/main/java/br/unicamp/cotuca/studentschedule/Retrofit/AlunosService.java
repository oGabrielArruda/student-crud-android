package br.unicamp.cotuca.studentschedule.Retrofit;

import br.unicamp.cotuca.studentschedule.Models.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlunosService {
    @GET("alunos")
    Call<Aluno[]> buscarAlunos();

    @GET("alunos/{ra}")
    Call<Aluno> buscarAluno(@Path("ra") int ra);

    @POST("alunos")
    Call<Aluno> cadastrarAluno(@Body Aluno aluno);

    @PUT("alunos")
    Call<Aluno> editarAluno(@Body Aluno aluno);

    @DELETE("alunos/{ra}")
    Call<Aluno> removerAluno(@Body Aluno aluno);
}
