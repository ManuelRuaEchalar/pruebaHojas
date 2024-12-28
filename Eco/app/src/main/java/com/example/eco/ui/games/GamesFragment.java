package com.example.eco.ui.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.eco.R;
import com.example.eco.databinding.FragmentGamesBinding;
import com.example.eco.ui.games.minigamecuatro.MiniGameCuatroActivity;
import com.example.eco.ui.games.minigamedos.MiniGameDosActivity;
import com.example.eco.ui.games.minigametres.MiniGameTresActivity;

public class GamesFragment extends Fragment {

    private FragmentGamesBinding binding;
    private GameProgressManager progressManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GamesViewModel gamesViewModel =
                new ViewModelProvider(this).get(GamesViewModel.class);

        binding = FragmentGamesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar el GameProgressManager
        progressManager = new GameProgressManager(getContext());

        // Referencia a los botones
        ImageButton btnGame1 = binding.btnGame1;
        ImageButton btnGame2 = binding.btnGame2;
        ImageButton btnGame3 = binding.btnGame3;
        ImageButton btnGame4 = binding.btnGame4;

        // Actualizar las imágenes según el estado guardado en la base de datos
        btnGame1.setImageResource(progressManager.isGameUnlocked(1) ?
                R.drawable.tank_icon : R.drawable.tank_icon_blocked);
        btnGame2.setImageResource(progressManager.isGameUnlocked(2) ?
                R.drawable.catch_icon : R.drawable.catch_icon_blocked);
        btnGame3.setImageResource(progressManager.isGameUnlocked(3) ?
                R.drawable.pick_icon : R.drawable.pick_icon_blocked);
        btnGame4.setImageResource(progressManager.isGameUnlocked(4) ?
                R.drawable.snake_icon : R.drawable.snake_icon_blocked);

        // Configuración de los clicks
        btnGame1.setOnClickListener(v -> {
            if (progressManager.isGameUnlocked(1)) {
                Intent intent = new Intent(getActivity(), MiniGameUnoActivity.class);
                startActivity(intent);
            }
        });

        btnGame2.setOnClickListener(v -> {
            if (progressManager.isGameUnlocked(2)) {
                Intent intent = new Intent(getActivity(), MiniGameDosActivity.class);
                startActivity(intent);
            }
        });

        btnGame3.setOnClickListener(v -> {
            if (progressManager.isGameUnlocked(3)) {
                Intent intent = new Intent(getActivity(), MiniGameTresActivity.class);
                startActivity(intent);
            }
        });

        btnGame4.setOnClickListener(v -> {
            if (progressManager.isGameUnlocked(4)) {
                Intent intent = new Intent(getActivity(), MiniGameCuatroActivity.class);
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