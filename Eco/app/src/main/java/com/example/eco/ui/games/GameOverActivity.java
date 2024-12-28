package com.example.eco.ui.games;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
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
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.example.eco.MainActivity;
import com.example.eco.R;

public class GameOverActivity extends AppCompatActivity {
    TextView tvPoints;
    TextView tvHighest;
    ImageView ivNewHighest;
    TextView textVResult;
    ImageButton btnRestart;
    ImageButton btnExit;
    SharedPreferences sharedPreferences;
    SoundPool soundPoolGO;
    int victorySoundId;
    int defeatSoundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();


        soundPoolGO = new SoundPool.Builder()
                .setMaxStreams(1)  // Maximum number of simultaneous streams
                .setAudioAttributes(audioAttributes)
                .build();

        victorySoundId = soundPoolGO.load(this, R.raw.victoria, 1);
        defeatSoundId = soundPoolGO.load(this, R.raw.derrota, 1);

//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over);
        tvPoints = findViewById(R.id.tvPoints);
        tvHighest = findViewById(R.id.tvHighest);
        ivNewHighest = findViewById(R.id.ivNewHighest);
        btnRestart = findViewById(R.id.btnRestart);
        textVResult = findViewById(R.id.textVResult);
        btnExit = findViewById(R.id.btnExit);


        int points = getIntent().getExtras().getInt("points");

        boolean won = getIntent().getExtras().getBoolean("won");
        if(won){
            textVResult.setText("¡VICTORIA!");
            soundPoolGO.play(victorySoundId, 1, 1, 1, 0, 1);
            //verde
            textVResult.setTextColor(getResources().getColor(R.color.victory_green));

            // Usar GameProgressManager para desbloquear el siguiente nivel
            GameProgressManager progressManager = new GameProgressManager(this);
            progressManager.unlockGame(2); // Desbloquea el juego 2
        }
        else {
            //rojo
            textVResult.setText("¡PERDISTE!");
            soundPoolGO.play(defeatSoundId, 1, 1, 1, 0, 1);
            textVResult.setTextColor(getResources().getColor(R.color.defeat_red));
        }

        tvPoints.setText(""+ points);


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
        Intent intent = new Intent(this, MiniGameUnoActivity.class);
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