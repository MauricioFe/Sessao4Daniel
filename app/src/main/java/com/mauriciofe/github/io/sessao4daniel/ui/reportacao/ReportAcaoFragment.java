package com.mauriciofe.github.io.sessao4daniel.ui.reportacao;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.mauriciofe.github.io.sessao4daniel.CallBack;
import com.mauriciofe.github.io.sessao4daniel.MainActivity;
import com.mauriciofe.github.io.sessao4daniel.MyAssycTask;
import com.mauriciofe.github.io.sessao4daniel.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import static android.app.Activity.RESULT_OK;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.BASE_URL;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_POST;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.REQUEST_IMAGE_CAPTURE;

public class ReportAcaoFragment extends Fragment {
    TextView txtUsuarioId;
    TextView txtNome;
    TextView txtEmail;
    Switch swRealatarAnonino;
    EditText edtRelato;
    Button btnCapturarImagem;
    TextView txtLatitude;
    TextView txtLongitude;
    ImageView imgRelatos;
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
        imgRelatos = root.findViewById(R.id.imgRelatos);
        Intent intent = getActivity().getIntent();
        try {
            txtEmail.setText(new JSONObject(intent.getStringExtra("usuarioLogado")).getString("email"));
            txtNome.setText(new JSONObject(intent.getStringExtra("usuarioLogado")).getString("nome"));
            txtUsuarioId.setText(new JSONObject(intent.getStringExtra("usuarioLogado")).getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        btnCapturarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent tiraFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(tiraFoto, REQUEST_IMAGE_CAPTURE);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent tiraFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(tiraFoto, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_IMAGE_CAPTURE == requestCode && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgRelatos.setImageBitmap(bitmap);
        }
    }

    private void cadastrarRelato() {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("relato").value(edtRelato.getText().toString());
            js.key("imagem").value(nomeImagem);
            js.key("latitude").value(txtLongitude.getText().toString());
            js.key("longitude").value(txtLongitude.getText().toString());
            if (swRealatarAnonino.isChecked())
                js.key("usuarioId").value(null);
            else
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