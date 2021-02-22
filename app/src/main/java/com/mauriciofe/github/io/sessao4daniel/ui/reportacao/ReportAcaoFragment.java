package com.mauriciofe.github.io.sessao4daniel.ui.reportacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.mauriciofe.github.io.sessao4daniel.CallBack;
import com.mauriciofe.github.io.sessao4daniel.MainActivity;
import com.mauriciofe.github.io.sessao4daniel.MyAssycTask;
import com.mauriciofe.github.io.sessao4daniel.R;

import org.json.JSONException;
import org.json.JSONStringer;

import static com.mauriciofe.github.io.sessao4daniel.Constantes.BASE_URL;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_POST;

public class ReportAcaoFragment extends Fragment {
    TextView txtUsuarioId;
    TextView txtNome;
    TextView txtEmail;
    Switch swRealatarAnonino;
    EditText edtRelato;
    Button btnCapturarImagem;
    TextView txtLatitude;
    TextView txtLongitude;
    Button btnLocalizar;
    Button btnSalvar;
    Button btnCancelar;
    String nomeImagem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reportacao, container, false);
        txtUsuarioId = root.findViewById(R.id.reportar_txtIdUsuario);
        txtNome = root.findViewById(R.id.reportar_txtNome);
        txtEmail = root.findViewById(R.id.reportar_txtEmail);
        swRealatarAnonino = root.findViewById(R.id.reportar_swRelatarAnonimamente);
        edtRelato = root.findViewById(R.id.reportar_edtRelato);
        btnCapturarImagem = root.findViewById(R.id.reportar_btnFotografar);
        txtLatitude = root.findViewById(R.id.reportar_txtLatitude);
        txtLongitude = root.findViewById(R.id.reportar_txtLogintude);
        btnLocalizar = root.findViewById(R.id.reportar_btnLocalizar);
        btnSalvar = root.findViewById(R.id.reportar_btnSalvar);
        btnCancelar = root.findViewById(R.id.reportar_btnCancelar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarRelato();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        return root;
    }

    private void cadastrarRelato() {

        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("relato").value(edtRelato.getText().toString());
            js.key("imagem").value(nomeImagem);
            js.key("latitude").value(txtLongitude.getText().toString());
            js.key("longitude").value(txtLongitude.getText().toString());
            js.key("usuarioId").value(txtUsuarioId.getText().toString());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyAssycTask.requestApi(BASE_URL + "relatos", METHOD_POST, js.toString(), new CallBack<String>() {
            @Override
            public void onComplete(String result) {
                if (result != null) {
                    Toast.makeText(getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}