package com.mauriciofe.github.io.sessao4daniel.ui.visualizar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mauriciofe.github.io.sessao4daniel.CallBack;
import com.mauriciofe.github.io.sessao4daniel.ListRelatosAdapter;
import com.mauriciofe.github.io.sessao4daniel.MainActivity;
import com.mauriciofe.github.io.sessao4daniel.MyAssycTask;
import com.mauriciofe.github.io.sessao4daniel.R;
import com.mauriciofe.github.io.sessao4daniel.models.Relato;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mauriciofe.github.io.sessao4daniel.Constantes.BASE_URL;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_GET;

public class VisualizarFragment extends Fragment {
    RecyclerView listRelatos;
    List<Relato> relatosList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_visualizar, container, false);
        listRelatos = root.findViewById(R.id.list_relatos);
        listRelatos.setLayoutManager(new LinearLayoutManager(getContext()));
        ListRelatosAdapter adapter = new ListRelatosAdapter(relatosList, getContext());
        listRelatos.setAdapter(adapter);
        MyAssycTask.requestApi(BASE_URL + "relatos", METHOD_GET, null, new CallBack<String>() {
            @Override
            public void onComplete(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Relato relato = new Relato();
                        relato.setId(jsonObject.getInt("id"));
                        relato.setLatitude(String.valueOf(jsonObject.getDouble("latitude")));
                        relato.setLongitude(String.valueOf(jsonObject.getDouble("longitude")));
                        String bacon = jsonObject.getString("usuarioId");
                        if (!bacon.equals("null")) {
                            relato.setUsuarioId(jsonObject.getInt("usuarioId"));
                            relato.setNomeUsuario(jsonObject.getJSONObject("usuario").getString("nome"));
                            relato.setTelefone(jsonObject.getJSONObject("usuario").getString("telefone"));
                        } else {
                            relato.setNomeUsuario("Anônimo");
                            relato.setTelefone("(##) #####-####");
                        }
                        relatosList.add(relato);
                    }
                    adapter.atualizaLista(relatosList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return root;
    }
}