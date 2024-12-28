package com.example.eco.ui.games.minigamedos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eco.MainActivity;
import com.example.eco.R;
import com.example.eco.ui.games.GameProgressManager;
import com.example.eco.ui.games.GamesFragment;

public class GameOverActivity extends AppCompatActivity {
    TextView tvPoints;
    TextView tvHighest;
    ImageView ivNewHighest;
    ImageButton btnRestart;
    ImageButton btnExit;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over_dos);
        tvPoints = findViewById(R.id.tvPoints);
        tvHighest = findViewById(R.id.tvHighest);
        ivNewHighest = findViewById(R.id.ivNewHighest);
        btnRestart = findViewById(R.id.btnRestart);
        btnExit = findViewById(R.id.btnExit);

        int points = getIntent().getExtras().getInt("points");
        String status = getIntent().getExtras().getString("status"); // Recibimos el estado

        tvPoints.setText("" + points);

// Verificamos si ganó o perdió
        TextView tvStatus = findViewById(R.id.tvStatus);
        if (status.equals("won")) {
            tvStatus.setText("¡Has ganado!");
            ivNewHighest.setVisibility(ImageView.GONE);  // Ocultamos el nuevo récord si ganó
            // Usar GameProgressManager para desbloquear el siguiente nivel
            GameProgressManager progressManager = new GameProgressManager(this);
            progressManager.unlockGame(3); // Desbloquea el juego 2
        } else {
            tvStatus.setText("Has perdido");
        }


        sharedPreferences = getSharedPreferences("game", MODE_PRIVATE);

        int highest = sharedPreferences.getInt("highest", 0);
        if (points > highest) {

            ivNewHighest.setVisibility(ImageView.VISIBLE);
            highest = points;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highest", highest);
            editor.apply();
        }
        tvHighest.setText(""+ highest);


        btnRestart.setOnClickListener(v -> {
            restartGame(v);
        });
        btnExit.setOnClickListener(v -> {
            exit(v);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void restartGame(View view) {
        Intent intent = new Intent(this, MiniGameDosActivity.class);
        startActivity(intent);
        finish();

    }

    public void exit(View view) {
        //cargar o redirigir a GamesFragment
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}