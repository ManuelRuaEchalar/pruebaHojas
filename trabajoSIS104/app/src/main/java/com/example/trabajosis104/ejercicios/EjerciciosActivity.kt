package com.example.trabajosis104.ejercicios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabajosis104.R

class EjerciciosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonEcuacion = findViewById<Button>(R.id.buttonEcuacion);
        val buttonComplejo = findViewById<Button>(R.id.buttonComplejo);
        val buttonDivRes = findViewById<Button>(R.id.buttonDivRes);
        val buttonRes = findViewById<Button>(R.id.buttonRes);
        val buttonSerie = findViewById<Button>(R.id.buttonSerie);
        val buttonRecursiva = findViewById<Button>(R.id.buttonRecursiva);
        val buttonValidar = findViewById<Button>(R.id.buttonValidar);
        val buttonSalir = findViewById<Button>(R.id.buttonEjercicioSalir);
        val buttonPersona = findViewById<Button>(R.id.buttonPersona);
        val buttonMultiplicacion = findViewById<Button>(R.id.buttonMultiplicacion);

        buttonEcuacion.setOnClickListener {
            val intent = Intent(this, EcuacionActivity::class.java)
            startActivity(intent)
        }

        buttonMultiplicacion.setOnClickListener {
            val intent = Intent(this, MultiplicacionActivity::class.java)
            startActivity(intent)
        }

        buttonValidar.setOnClickListener {
            val intent = Intent(this, StringValidationActivity::class.java)
            startActivity(intent)
        }

        buttonRecursiva.setOnClickListener {
            val intent = Intent(this, SumaActivity::class.java)
            startActivity(intent)
        }

        buttonComplejo.setOnClickListener {
            val intent = Intent(this, ComplejoActivity::class.java)
            startActivity(intent)
        }

        buttonDivRes.setOnClickListener {
            val intent = Intent(this, DivResActivity::class.java)
            startActivity(intent)
        }

        buttonRes.setOnClickListener {
            val intent = Intent(this, SumaMainActivity::class.java)
            startActivity(intent)
        }

        buttonSerie.setOnClickListener {
            val intent = Intent(this, ManualSumActivity::class.java)
            startActivity(intent)
        }

        buttonPersona.setOnClickListener {
            val intent = Intent(this, PersonViewActivity::class.java)
            startActivity(intent)
        }

        buttonSalir.setOnClickListener {
            finish()
        }

    }
}