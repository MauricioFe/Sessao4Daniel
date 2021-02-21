package com.mauriciofe.github.io.sessao4daniel;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhe_relato, menu);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            MenuItem item = menu.findItem(R.id.action_edit);
            MenuItem item2 = menu.findItem(R.id.action_delete);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Toast.makeText(this, "editando", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.action_delete)
            Toast.makeText(this, "Excluind", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}