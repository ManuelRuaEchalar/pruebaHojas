package com.example.eco.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.eco.databinding.FragmentInfoBinding;
import com.example.eco.ui.info.aprendiendo.AprendiendoActivity;

public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;
    private Button buttonAprender;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InfoViewModel infoViewModel =
                new ViewModelProvider(this).get(InfoViewModel.class);

        binding = FragmentInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonAprender = binding.buttonAprender;
        buttonAprender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de aprendizaje
                Intent intent = new Intent(getActivity(), AprendiendoActivity.class);
                startActivity(intent);
            }
        });
        
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}