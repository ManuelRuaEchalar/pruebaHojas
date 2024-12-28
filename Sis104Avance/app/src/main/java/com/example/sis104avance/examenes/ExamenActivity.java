package com.example.sis104avance.examenes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sis104avance.MainActivity;
import com.example.sis104avance.R;
import com.example.sis104avance.ejercicios.EjerciciosActivity;

public class ExamenActivity extends AppCompatActivity {

    Button button_PrimerParcial, button_SegundoParcial, button_Final, button_SalirFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_examen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button_PrimerParcial = findViewById(R.id.button_PrimerParcial);
        button_SegundoParcial = findViewById(R.id.button_Segundo_Parcial);
        button_Final = findViewById(R.id.button_Examen_Final);
        button_SalirFinal = findViewById(R.id.button_SalirFinal);

        button_SalirFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}