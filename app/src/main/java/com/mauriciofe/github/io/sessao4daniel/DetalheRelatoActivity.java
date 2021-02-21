package com.mauriciofe.github.io.sessao4daniel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mauriciofe.github.io.sessao4daniel.models.Relato;

public class DetalheRelatoActivity extends AppCompatActivity {

    TextView txtRelato;
    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtNomeRelator;
    TextView txtTelefone;
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_relato);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Relato relato = (Relato) getIntent().getSerializableExtra("relato");
        txtRelato = findViewById(R.id.txtRelato);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        txtNomeRelator = findViewById(R.id.txtNomeRelator);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);

        txtRelato.setText(relato.getRelato());
        txtLatitude.setText(relato.getLatitude());
        txtLongitude.setText(relato.getLongitude());
        txtNomeRelator.setText(relato.getNomeUsuario());
        txtTelefone.setText(relato.getTelefone());
        txtEmail.setText(relato.getEmail());
    }
}