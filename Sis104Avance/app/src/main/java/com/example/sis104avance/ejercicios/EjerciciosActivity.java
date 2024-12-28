package com.example.sis104avance.ejercicios;

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
import com.example.sis104avance.examenes.ExamenActivity;

public class EjerciciosActivity extends AppCompatActivity {

    Button button_Ecuacion2Grado, button_Estadistica, button_SalirEjercicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button_Ecuacion2Grado = findViewById(R.id.button_Ecuacion2doGrado);
        button_SalirEjercicios = findViewById(R.id.button_Salir_Ejercicios);
        button_Estadistica = findViewById(R.id.button_Estadistica);

        button_Ecuacion2Grado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EjerciciosActivity.this, EcuacionActivity.class);
                startActivity(intent);
            }
        });

        button_Estadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EjerciciosActivity.this, EstadisticaActivity.class);
                startActivity(intent);
            }
        });

        button_SalirEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EjerciciosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}