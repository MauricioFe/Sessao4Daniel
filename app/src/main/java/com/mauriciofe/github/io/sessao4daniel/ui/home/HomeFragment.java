package com.mauriciofe.github.io.sessao4daniel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mauriciofe.github.io.sessao4daniel.R;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView title = root.findViewById(R.id.text_title_home);
        final TextView content = root.findViewById(R.id.text_content_home);
        title.setText("O que fazemos?");
        content.setText("WS Towers é uma empresa que detém direitos de uso em estádios de todo o país. Exemplos de uso são: partidas de futebol, shows, visitas etc. \n" +
                "Por ser uma grande empresa a WS Tower também possui responsabilidade socioambiental. Desta forma, a partir desse aplicativo android\n" +
                "que conta com a ajuda das pessoa ao redor do país, para denunciar atentados contra o meio ambiente. ");

        return root;
    }
}