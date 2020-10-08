package br.unicamp.cotuca.studentschedule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.unicamp.cotuca.studentschedule.Models.Aluno;
import br.unicamp.cotuca.studentschedule.R;

public class AlunoAdapter extends ArrayAdapter<Aluno> {
    Context context;
    int layoutResourceId;
    List<Aluno> dados;

    public AlunoAdapter(@NonNull Context context, int resource, @NonNull List<Aluno> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.dados = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(this.layoutResourceId, parent, false);
        }

        TextView ra = (TextView) view.findViewById(R.id.tvRa);
        TextView nome = (TextView) view.findViewById(R.id.tvNome);
        TextView email = (TextView) view.findViewById(R.id.tvEmail);

        Aluno aluno = this.dados.get(position);
        ra.setText("Ra:    " + aluno.getRa());
        nome.setText("Nome:  " + aluno.getNome());
        email.setText("Email: " + aluno.getEmail());

        return view;
    }
}
