package com.mauriciofe.github.io.sessao4daniel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONStringer;

import static com.mauriciofe.github.io.sessao4daniel.Constantes.BASE_URL;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_GET;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_POST;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtSenha;
    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.username);
        edtSenha = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLogin(getJsonBody());
            }
        });
    }


    private String getJsonBody() {

        try {
            JSONStringer js = new JSONStringer();
            js.object();
            js.key("email").value(edtEmail.getText().toString());
            js.key("senha").value(edtSenha.getText().toString());
            js.endObject();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void realizarLogin(String jsonBody) {
        MyAssycTask.requestApi(BASE_URL + "usuarios", METHOD_POST, jsonBody, new CallBack<String>() {
            @Override
            public void onComplete(String result) {
                if (result != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("usuarioLogado", result);
                    startActivity(intent);
                }
            }
        });
    }
}