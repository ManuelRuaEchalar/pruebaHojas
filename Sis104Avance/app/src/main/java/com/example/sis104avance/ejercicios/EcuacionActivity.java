package com.example.sis104avance.ejercicios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sis104avance.MainActivity;
import com.example.sis104avance.R;

public class EcuacionActivity extends AppCompatActivity {

    EditText editTextA, editTextB, editTextC;
    Button button_SolucionEcuacion,button_salir;
    TextView textViewResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ecuacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);
        button_SolucionEcuacion = findViewById(R.id.button_ResolverEcuacion2doGrado);
        button_salir = findViewById(R.id.button_Salir_Ecuacion2doGrado);
        textViewResultado = findViewById(R.id.textViewResultado_Ecuacion2doGrado);

        button_SolucionEcuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                double a = Double.parseDouble(editTextA.getText().toString());
                double b = Double.parseDouble(editTextB.getText().toString());
                double c = Double.parseDouble(editTextC.getText().toString());
                Ecuacion ecuacion = new Ecuacion(a,b,c);
                textViewResultado.setText(ecuacion.Raices());
            }
        });

        button_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EcuacionActivity.this, EjerciciosActivity.class);
                startActivity(intent);
            }
        });

    }
}