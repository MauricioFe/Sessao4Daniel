package com.mauriciofe.github.io.sessao4daniel.ui.reportacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mauriciofe.github.io.sessao4daniel.R;

public class ReportAcaoFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reportacao, container, false);
        return root;
    }
}