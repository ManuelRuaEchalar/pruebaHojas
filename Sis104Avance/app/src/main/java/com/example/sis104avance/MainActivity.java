package com.example.sis104avance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sis104avance.ejercicios.EjerciciosActivity;
import com.example.sis104avance.examenes.ExamenActivity;
import com.example.sis104avance.graficos.GraficosActivity;

public class MainActivity extends AppCompatActivity {

    Button button_Ejercicio, button_Examen, button_BaseDatos, button_Grafico, button_Salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button_Ejercicio = findViewById(R.id.button_Ejercicios);
        button_Examen = findViewById(R.id.button_Examenes);
        button_BaseDatos = findViewById(R.id.button_BaseDatos);
        button_Grafico = findViewById(R.id.button_Graficos);
        button_Salir = findViewById(R.id.button_Salir);

        button_Ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EjerciciosActivity.class);
                startActivity(intent);
            }
        });

        button_Examen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExamenActivity.class);
                startActivity(intent);
            }
        });

        button_Grafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraficosActivity.class);
                startActivity(intent);
            }
        });

        button_Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
            }
        });
    }
}