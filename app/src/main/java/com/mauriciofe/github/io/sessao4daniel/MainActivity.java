package com.mauriciofe.github.io.sessao4daniel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    TextView edtEmail;
    TextView edtNome;
    int funcaoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String result = intent.getStringExtra("usuarioLogado");
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        try {
            funcaoId = new JSONObject(result).getInt("funcaoId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Menu menuNav = navigationView.getMenu();
        MenuItem navVisualizar = menuNav.findItem(R.id.nav_visualizar);
        if (funcaoId == 2) {
            navVisualizar.setEnabled(false);
        }
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_report, R.id.nav_visualizar)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        edtEmail = headerView.findViewById(R.id.header_txtEmail);
        edtNome = headerView.findViewById(R.id.header_txtNome);

        try {
            edtEmail.setText(new JSONObject(result).getString("email"));
            edtNome.setText(new JSONObject(result).getString("nome"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_info)
            new AlertDialog.Builder(this)
                    .setTitle("Informação")
                    .setMessage("V1.0.0 Desenvolvido por Maurício Lacerda MG durante a seletiva nacional de 2020.")
                    .setNeutralButton("OK", null)
                    .show();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_exit) {
            finish();
            return false;
        } else {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.onNavDestinationSelected(item, navController);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
    }
}