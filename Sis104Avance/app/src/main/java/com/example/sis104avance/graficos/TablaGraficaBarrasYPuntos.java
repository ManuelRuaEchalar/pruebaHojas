package com.example.sis104avance.graficos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sis104avance.R;

public class TablaGraficaBarrasYPuntos extends AppCompatActivity {

    int a;
    int b;
    int c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtén los valores del Intent dentro del onCreate
        a = getIntent().getIntExtra("datoA", 0);
        b = getIntent().getIntExtra("datoB", 0);
        c = getIntent().getIntExtra("datoC", 0);

        setContentView(new BarrasYPuntos(TablaGraficaBarrasYPuntos.this, a, b, c));

    }
}