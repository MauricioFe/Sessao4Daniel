package com.mauriciofe.github.io.sessao4daniel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mauriciofe.github.io.sessao4daniel.models.Relato;

import java.util.List;

public class ListRelatosAdapter extends RecyclerView.Adapter<ListRelatosAdapter.ListRelatosViewHolder> {

    public ListRelatosAdapter(List<Relato> relatosList, Context context) {
        this.relatosList = relatosList;
        this.context = context;
    }

    private List<Relato> relatosList;
    private Context context;

    @NonNull
    @Override
    public ListRelatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_list_relatos, parent, false);
        return new ListRelatosViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRelatosViewHolder holder, int position) {
        Relato relato = relatosList.get(position);
        holder.vincula(relato);
    }

    @Override
    public int getItemCount() {
        return relatosList.size();
    }

    public class ListRelatosViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomeRelator;
        TextView txtTelefone;
        TextView txtLatitude;
        TextView txtLongitude;

        public ListRelatosViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeRelator = itemView.findViewById(R.id.item_txtNomeRelator);
            txtTelefone = itemView.findViewById(R.id.item_txtTelefone);
            txtLatitude = itemView.findViewById(R.id.item_txtLatitude);
            txtLongitude = itemView.findViewById(R.id.item_txtLongitude);
        }

        public void vincula(Relato relato) {
            //vincular dados da lista para  tela
            txtNomeRelator.setText(relato.getNomeUsuario());
            txtTelefone.setText(relato.getTelefone());
            txtLatitude.setText(relato.getLatitude());
            txtLongitude.setText(relato.getLongitude());
        }
    }
}
