package com.example.sis104avance.graficos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sis104avance.R;

public class GraficoBarraActivity extends AppCompatActivity {

    EditText editTextDatoA, editTextDatoB, editTextDatoC;
    Button buttonGraficarBarras, buttonGraficarBarrasYPuntos, buttonGraficarTorta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grafico_barra);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextDatoA = findViewById(R.id.editTextDatoA);
        editTextDatoB = findViewById(R.id.editTextDatoB);
        editTextDatoC = findViewById(R.id.editTextDatoC);

        buttonGraficarBarras = findViewById(R.id.buttonGraficarBarras);
        buttonGraficarBarrasYPuntos = findViewById(R.id.buttonGraficarBarrasYPuntos);
        buttonGraficarTorta = findViewById(R.id.buttonGraficarTorta);

        buttonGraficarBarras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int datoA = Integer.parseInt(editTextDatoA.getText().toString());
                int datoB = Integer.parseInt(editTextDatoB.getText().toString());
                int datoC = Integer.parseInt(editTextDatoC.getText().toString());

                Intent intent = new Intent(GraficoBarraActivity.this, TablaGraficaBarraActivity.class);
                //enviar los datosA,B,C
                intent.putExtra("datoA", datoA);
                intent.putExtra("datoB", datoB);
                intent.putExtra("datoC", datoC);
                startActivity(intent);

            }
        });

        buttonGraficarBarrasYPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int datoA = Integer.parseInt(editTextDatoA.getText().toString());
                int datoB = Integer.parseInt(editTextDatoB.getText().toString());
                int datoC = Integer.parseInt(editTextDatoC.getText().toString());

                Intent intent = new Intent(GraficoBarraActivity.this, TablaGraficaBarrasYPuntos.class);
                //enviar los datosA,B,C
                intent.putExtra("datoA", datoA);
                intent.putExtra("datoB", datoB);
                intent.putExtra("datoC", datoC);
                startActivity(intent);

            }
        });


        buttonGraficarTorta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int datoA = Integer.parseInt(editTextDatoA.getText().toString());
                int datoB = Integer.parseInt(editTextDatoB.getText().toString());
                int datoC = Integer.parseInt(editTextDatoC.getText().toString());

                Intent intent = new Intent(GraficoBarraActivity.this, TablaTortaActivity.class);
                //enviar los datosA,B,C
                intent.putExtra("datoA", datoA);
                intent.putExtra("datoB", datoB);
                intent.putExtra("datoC", datoC);
                startActivity(intent);

            }
        });


    }
}