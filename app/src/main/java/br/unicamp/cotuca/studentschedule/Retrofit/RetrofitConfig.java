package br.unicamp.cotuca.studentschedule.Retrofit;

import br.unicamp.cotuca.studentschedule.Models.Aluno;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("meuip:3000/aluno/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AlunosService getAlunosService() {
        return this.retrofit.create(AlunosService.class);
    }
}
