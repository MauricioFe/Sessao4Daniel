package com.mauriciofe.github.io.sessao4daniel.ui.visualizar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mauriciofe.github.io.sessao4daniel.R;

public class VisualizarFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_visualizar, container, false);
        return root;
    }
}