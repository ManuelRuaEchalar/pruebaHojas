package com.example.sis104avance.graficos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sis104avance.R;
import com.example.sis104avance.ejercicios.EcuacionActivity;
import com.example.sis104avance.ejercicios.EjerciciosActivity;

public class GraficosActivity extends AppCompatActivity {

    Button buttonGraficos2d, buttonGraficosFractal, buttonGraficoBarras, buttonGraficosSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graficos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonGraficos2d = findViewById(R.id.buttonGraficos2d);
        buttonGraficosFractal = findViewById(R.id.buttonGraficosFractal);
        buttonGraficosSalir = findViewById(R.id.buttonGraficosSalir);
        buttonGraficoBarras = findViewById(R.id.buttonGraficoBarras);

        buttonGraficoBarras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraficosActivity.this, GraficoBarraActivity.class);
                startActivity(intent);
            }
        });

        buttonGraficos2d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraficosActivity.this, Grafico2dActivity.class);
                startActivity(intent);
            }
        });

        buttonGraficosFractal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraficosActivity.this, FractalActivity.class);
                startActivity(intent);
            }
        });
    }
}