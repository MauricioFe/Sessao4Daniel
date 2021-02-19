package com.mauriciofe.github.io.sessao4daniel;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONStringer;

import static com.mauriciofe.github.io.sessao4daniel.Constantes.BASE_URL;
import static com.mauriciofe.github.io.sessao4daniel.Constantes.METHOD_POST;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtSenha;
    Button btnLogin;
    int cont = 0;

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
                realizarLogin(getJsonBody(), v);
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

    private void realizarLogin(String jsonBody, View v) {
        MyAssycTask.requestApi(BASE_URL + "usuarios", METHOD_POST, jsonBody, new CallBack<String>() {
            @Override
            public void onComplete(String result) {
                if (result != null && !result.equals("")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("usuarioLogado", result);
                    startActivity(intent);
                } else {
                    cont++;
                    edtEmail.getBackground().mutate().setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.red), PorterDuff.Mode.SRC_ATOP);
                    edtSenha.getBackground().mutate().setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Snackbar.make(v, "Usuário/Senha inválidos", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    if (cont == 3) {
                        edtEmail.setEnabled(false);
                        edtSenha.setEnabled(false);
                        btnLogin.setEnabled(false);
                        cont = 0;
                        Snackbar.make(v, "Login Bloqueado: aguarde 30s", 30000)
                                .setAction("Action", null).show();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edtEmail.setEnabled(true);
                                edtSenha.setEnabled(true);
                                btnLogin.setEnabled(true);
                                edtEmail.getBackground().mutate().setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                                edtSenha.getBackground().mutate().setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.teal_200), PorterDuff.Mode.SRC_ATOP);
                            }
                        }, 30000);
                    }
                }
            }
        });
    }
}