package com.example.eco.ui.games.minigamecuatro;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eco.R;

import java.util.Random;

public class MiniGameCuatroActivity extends AppCompatActivity {

    private GameView gameView;
    private Button buttonStartGame;
    public static int randomImageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game_cuatro);  // Cargar el layout XML

        // Generar número aleatorio entre 1 y 6
        Random random = new Random();
        randomImageIndex = random.nextInt(6) + 1;

        buttonStartGame = findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(v -> {
            GameView gameView = new GameView(this);
            setContentView(gameView);
        });
    }
}
