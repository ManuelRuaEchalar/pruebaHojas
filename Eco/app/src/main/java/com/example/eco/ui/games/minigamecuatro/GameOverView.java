package com.example.eco.ui.games.minigamecuatro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.eco.MainActivity;
import com.example.eco.R;
import com.example.eco.ui.games.GameProgressManager;
import com.example.eco.ui.games.GamesFragment;

public class GameOverView extends Activity {
    private TextView tvStatus;
    private TextView tvPoints;
    private ImageButton btnExit, btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_view);

        // Obtener referencias de las vistas
        tvStatus = findViewById(R.id.tvStatus);
        tvPoints = findViewById(R.id.tvPoints);
        btnExit = findViewById(R.id.btnExit);
        btnRestart = findViewById(R.id.btnRestart);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String status = intent.getStringExtra("status");
        int points = intent.getIntExtra("points", 0);

        // Actualizar el texto según el estado del juego
        if (status.equals("won")) {
            tvStatus.setText("¡Has ganado!");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            // Usar GameProgressManager para desbloquear el siguiente nivel
            GameProgressManager progressManager = new GameProgressManager(this);
        } else {
            tvStatus.setText("Has perdido");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        // Mostrar los puntos
        tvPoints.setText(String.valueOf(points));

        // Configurar el botón de salir
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cargar o redirigir a MainActivity
                Intent intent = new Intent(GameOverView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar el botón de reiniciar
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reiniciar el juego
                Intent restartIntent = new Intent(GameOverView.this, MiniGameCuatroActivity.class);
                startActivity(restartIntent);
                finish();  // Cerrar la actividad actual
            }
        });
    }
}
