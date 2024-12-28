package com.example.eco.ui.games.minigamedos;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eco.R;

import java.util.Random;

public class MiniGameDosActivity extends AppCompatActivity {
    Button buttonStartGame;
    public static int randomImageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game_dos);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
